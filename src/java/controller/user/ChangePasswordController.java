package controller.user;

import dal.AccountDBContext;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/user/changepassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDBContext adc = new AccountDBContext();
        Account currentAccount = (Account) request.getSession().getAttribute("account");

        if (currentAccount == null) {
            request.setAttribute("err", "You must be logged in to change your password.");
            forwardToChangePassword(request, response);
            return;
        }

        String oldPassword = request.getParameter("oldpass");
        String newPassword = request.getParameter("newpass");
        String confirmPassword = request.getParameter("confirmpass");

        // Validate input fields
        if (oldPassword == null || newPassword == null || confirmPassword == null
                || oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            request.setAttribute("err", "All fields are required.");
            forwardToChangePassword(request, response);
            return;
        }

        // Validate password length
        if (newPassword.length() < 8) {
            request.setAttribute("err", "Password must not be less than 8 characters.");
            forwardToChangePassword(request, response);
            return;
        }

        // Validate new password and confirm password match
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("err", "New password and confirm password do not match.");
            forwardToChangePassword(request, response);
            return;
        }

        // Process password change
        try {
            

            // Update password in the database
            adc.updatePasswordByUsername(newPassword, currentAccount.getUsername());

            // Update the password in the cookie (if needed)
            Cookie passwordCookie = new Cookie("password", newPassword);
            passwordCookie.setMaxAge(60 * 60 * 24 * 7); // 1 week
            response.addCookie(passwordCookie);

            request.setAttribute("success", "Password changed successfully.");
            forwardToChangePassword(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "An error occurred while changing the password.");
            forwardToChangePassword(request, response);
        }
    }

    private void forwardToChangePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/user/changepassword.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Change Password Servlet";
    }
}
