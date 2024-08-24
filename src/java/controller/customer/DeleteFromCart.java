package controller.customer;

import controller.user.BaseRequiredAuthenticationController;
import dal.CartDao;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteFromCart", urlPatterns = {"/customer/DeleteFromCart"})
public class DeleteFromCart extends BaseRequiredAuthenticationController {

    @Override
    public String getServletInfo() {
        return "Handles deletion of items from the cart.";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        Account account1 = (Account) req.getSession().getAttribute("account");

        if (account1 == null) {
            resp.sendRedirect("login");
            return;
        }

        int userId = account1.getId();
        int productDetailId = Integer.parseInt(req.getParameter("itemId"));

        CartDao cartDao = new CartDao();
        cartDao.deleteFromCart(userId, productDetailId);

        resp.sendRedirect("CartController"); // Redirect back to the cart view after deletion
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
