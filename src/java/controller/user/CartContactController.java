package controller.user;

import dal.CartDao;
import dal.UserDBContext;
import entity.Account;
import entity.Product;
import entity.User;
import java.io.IOException;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CartContactController", urlPatterns = {"/CartContactController"})
public class CartContactController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the account from the session
        Account account = (Account) request.getSession().getAttribute("account");

        // Redirect to login if the user is not logged in
        if (account == null) {
            response.sendRedirect("login");
            return;
        }

        // Fetch the user details using the account ID
        UserDBContext userDao = new UserDBContext();
        User loggedInUser = userDao.getUserByAccountId(account.getId());

        // Redirect to login if no user is found
        if (loggedInUser == null) {
            response.sendRedirect("login");
            return;
        }

        // Initialize the CartDao to retrieve cart items
        CartDao cartDao = new CartDao();
        Map<Integer, Map<Product, Double>> cartItems = cartDao.getCartItemsByUserId(loggedInUser.getId());

        // Set the retrieved items and user details as request attributes
        request.setAttribute("cartItems", cartItems);
        request.setAttribute("user", loggedInUser);

        // Forward the request to the JSP page to display the cart details
        request.getRequestDispatcher("./view/user/cartContact.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Use the same logic for POST requests as GET requests
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles the contact information and cart details.";
    }
}
