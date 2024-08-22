package controller.user;

import dal.CartDao;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteFromCart", urlPatterns = {"/DeleteFromCart"})
public class DeleteFromCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");

        if (account == null) {
            response.sendRedirect("login");
            return;
        }

        int userId = account.getId();
        int productDetailId = Integer.parseInt(request.getParameter("itemId"));

        CartDao cartDao = new CartDao();
        cartDao.deleteFromCart(userId, productDetailId);

        response.sendRedirect("CartController"); // Redirect back to the cart view after deletion
    }

    @Override
    public String getServletInfo() {
        return "Handles deletion of items from the cart.";
    }
}
