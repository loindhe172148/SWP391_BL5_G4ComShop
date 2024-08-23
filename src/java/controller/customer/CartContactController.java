package controller.customer;

import controller.user.BaseRequiredAuthenticationController;
import dal.CartDao;
import dal.UserDBContext;
import entity.Account;
import entity.Product;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "CartContactController", urlPatterns = {"/customer/CartContactController"})
public class CartContactController extends BaseRequiredAuthenticationController {

    @Override
    public String getServletInfo() {
        return "Handles the contact information and cart details.";
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

        // Fetch the user details using the account ID
        UserDBContext userDao = new UserDBContext();
        User loggedInUser = userDao.getUserByAccountId(account1.getId());
        System.out.println(account1.getId());
        // Redirect to login if no user is found
        if (loggedInUser == null) {
            resp.sendRedirect("login");
            return;
        }

        // Initialize the CartDao to retrieve cart items
        CartDao cartDao = new CartDao();
        int x = account1.getId();
        System.out.println(x);
        Map<Integer, Map<Product, Double>> cartItems = cartDao.getCartItemsByUserId(account1.getId());
        
        // Set the retrieved items and user details as request attributes
        req.setAttribute("cartItems", cartItems);
        req.setAttribute("user", loggedInUser);
        // Forward the request to the JSP page to display the cart details
        req.getRequestDispatcher("/view/customer/cartContact.jsp").forward(req, resp);
    }
}
