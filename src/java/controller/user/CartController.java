package controller.user;

import dal.CartDao;
import entity.Account;
import entity.Product;
import java.io.IOException;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Servlet implementation class CartController
 */
@WebServlet(name = "CartController", urlPatterns = {"/CartController"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the current session's account
        Account account = (Account) request.getSession().getAttribute("account");

        // If no account is found, redirect to login page
        if (account == null) {
            response.sendRedirect("login");
            return;
        }

        int userId = account.getId();

        // Fetch cart items using CartDao
        CartDao cartDao = new CartDao();
        Map<Integer, Map<Product, Double>> cartItems = cartDao.getCartItemsByUserId(userId);

        // Set attributes for use in JSP
        request.setAttribute("cartItems", cartItems);
        request.getSession().setAttribute("acc", account);

        // Forward the request to the cart JSP
        request.getRequestDispatcher("./view/user/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // If POST requests are needed, process them here
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles user cart operations";
    }
}
