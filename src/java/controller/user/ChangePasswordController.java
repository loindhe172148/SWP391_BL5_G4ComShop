package controller.user;

import dal.AccountDBContext;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePasswordController extends BaseRequiredAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        AccountDBContext adc = new AccountDBContext();
        Account currentAccount = (Account) req.getSession().getAttribute("account");

        String oldPassword = req.getParameter("oldpass");
        String newPassword = req.getParameter("newpass");
        String confirmPassword = req.getParameter("confirmpass");

        String username = currentAccount.getUsername();
        String storedPassword = adc.getPassbyUsername(username);

        if (storedPassword == null || !storedPassword.equals(oldPassword)) {
            req.setAttribute("errorChange", "Invalid old password.");
            resp.sendRedirect("/productHome?id=" + currentAccount.getId());
            return;
        }

        if (oldPassword.equals(newPassword)) {
            req.setAttribute("errorChange", "New password cannot be the same as the old password.");
            req.getRequestDispatcher("/productHome?id=" + currentAccount.getId()).forward(req, resp);
            return;
        }

        if (oldPassword == null || newPassword == null || confirmPassword == null
                || oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            req.setAttribute("errorChange", "All fields are required.");
            req.getRequestDispatcher("/productHome?id=" + currentAccount.getId()).forward(req, resp);
            return;
        }

        if (newPassword.length() < 8) {
            req.setAttribute("errorChange", "Password must not be less than 8 characters.");
            req.getRequestDispatcher("/productHome?id=" + currentAccount.getId()).forward(req, resp);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            req.setAttribute("errorChange", "New password and confirm password do not match.");
            req.getRequestDispatcher("/productHome?id=" + currentAccount.getId()).forward(req, resp);
            return;
        }

        try {
            adc.updatePasswordByUsername(newPassword, currentAccount.getUsername());
            req.setAttribute("successChange", true);
            req.getRequestDispatcher("/productHome?id=" + currentAccount.getId()).forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorChange", "An error occurred while changing the password.");
            req.getRequestDispatcher("/productHome?id=" + currentAccount.getId()).forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
    }
}
