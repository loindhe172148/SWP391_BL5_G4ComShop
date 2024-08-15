package controller.user;

import dal.AccountDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import entity.Account;
import java.io.IOException;
import util.EmailService;

@WebServlet(name = "ForgotAccountController", urlPatterns = {"/forgot"})
public class ForgotAccountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the forgot password view
        request.getRequestDispatcher("./view/user/forgotpassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDBContext adc = new AccountDBContext();
        EmailService emailService = new EmailService();
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        try {
            // Retrieve account by username and email
            Account account = adc.getAccountByUsernameAndEmail(username, email);

            if (account == null) {
                // If account is not found, set an error message and forward to the forgot password view
                request.setAttribute("err", "Unable to find your account! Please try again.");
                request.getRequestDispatcher("./view/user/forgotpassword.jsp").forward(request, response);
                return;
            }

            // Generate a new default password
            String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*";
            String newDefaultPass = EmailService.generatePassword(str, 8);
            String emailText = "Your default password is: " + newDefaultPass + ". Please use this password to log in and change it immediately.";

            // Send email with the default password
            emailService.sendEmail(email, "Your Default Password", emailText);

            // Set the default password and other session attributes
            HttpSession session = request.getSession();
            session.setAttribute("defaultPass", newDefaultPass);
            session.setMaxInactiveInterval(60); // 1 minute session timeout
            session.setAttribute("email", email);
            session.setAttribute("username", username);

            // Forward to the default password view
            request.getRequestDispatcher("./view/user/defaultpass.jsp").forward(request, response);

        } catch (Exception e) {
            // Log the error and send a server error response
            log("Error processing forgot password request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred. Please try again later.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles forgot password requests and sends a default password to the user.";
    }
}