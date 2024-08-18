package controller.user;

import dal.AccountDBContext;
import dal.UserDBContext;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.EmailService;

/**
 * Handles password reset requests.
 */
@WebServlet(name = "ResetPasswordController", urlPatterns = {"/reset-password"})
public class ResetPasswordController extends BaseRequiredAuthenticationController {

    @Override
    public String getServletInfo() {
        return "Handles password reset requests.";
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String username = req.getParameter("username");
        String mail = req.getParameter("email");
        req.setAttribute("mail", mail);
        req.setAttribute("username", username);
        EmailService emailService = new EmailService();

        if (!isValidEmail(mail)) {
            req.setAttribute("error", "Invalid email format.");
            req.getRequestDispatcher("/view/user/resetpass.jsp").forward(req, resp);
            return;
        }
        try {
            AccountDBContext adc = new AccountDBContext();
            int accid = adc.getAccountIDByUsername(username);

            UserDBContext user = new UserDBContext();
            Account acc = new Account();
            acc = adc.checkAccountExist(username);
            if (acc != null) {
                if (user.checkMailExist(accid, mail)) {

                    String resetpass = generatePassword();
                    adc.updatePasswordByUsername(resetpass, username);
                    emailService.sendEmail(mail, "Reset Password",
                            "Dear user,\nHere is your password:\n" + resetpass + "\n");
                    req.getRequestDispatcher("/view/user/login.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "Mail is not correct.");
                    req.getRequestDispatcher("/view/user/resetpass.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("error", "Invalid username.");
                req.getRequestDispatcher("/view/user/resetpass.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while resetting the password. Please try again later.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        req.getRequestDispatcher("/view/user/resetpass.jsp").forward(req, resp);
    }
}
