package controller.user;

import dal.AccountDBContext;
import dal.UserAccountDBContext;
import entity.UserAccount;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import util.EmailService;

public class ResetPasswordController extends HttpServlet {

    @Override
    public String getServletInfo() {
        return "Handles password reset requests.";
    }

    public static String generatePassword() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 8; i++) {
            sb.append(str.charAt(rand.nextInt(str.length() - 1)));
        }
        return sb.toString();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = req.getParameter("input");
        req.setAttribute("input", input);
        EmailService emailService = new EmailService();
        AccountDBContext adc = new AccountDBContext();
        UserAccount uc;
        UserAccountDBContext ucd = new UserAccountDBContext();
        try {
            uc = ucd.getEmailandUsername(input);
            if (uc != null) {
                String resetpass = generatePassword();
                adc.updatePasswordByUsername(resetpass, input);
                emailService.sendEmail(uc.getMail(), "Reset Password",
                        "Dear user,\nHere is your username: " + uc.getUsername() + "\nAnd your password: " + resetpass + "\n");
                req.setAttribute("resetSuccess", true);
                req.getRequestDispatcher("/productHome").forward(req, resp);
            } else {
                req.setAttribute("errorReset", "Incorrect username or email");
                req.getRequestDispatcher("/productHome").forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while resetting the password");
        }
    }
}
