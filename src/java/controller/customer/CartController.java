package controller.customer;

import controller.user.BaseRequiredAuthenticationController;
import dal.CartDao;
import entity.Account;
import entity.Product;
import java.io.IOException;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CartController
 */
@WebServlet(name = "CartController", urlPatterns = {"/customer/CartController"})
public class CartController extends BaseRequiredAuthenticationController {

    @Override
    public String getServletInfo() {
        return "Handles user cart operations";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        
        Account account1 = (Account) req.getSession().getAttribute("account");

        if (account1 == null) {
            resp.sendRedirect("login");
            return;
        }

        int userId = account1.getId();

        CartDao cartDao = new CartDao();
        Map<Integer, Map<Product, Double>> cartItems = cartDao.getCartItemsByUserId(userId);

        req.setAttribute("cartItems", cartItems);
        req.getSession().setAttribute("acc", account1);

        req.getRequestDispatcher("/view/customer/cart.jsp").forward(req, resp);
    }
    
}
