package controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.EmailService;

import java.io.IOException;

@WebServlet(name = "VerifyAccount", urlPatterns = {"/verify"})
public class VerifyAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve email from session
        String email = (String) request.getSession().getAttribute("email");
        
        if (email == null || email.isEmpty()) {
            response.sendRedirect("signup"); // Redirect to signup if email is not set
            return;
        }

        // Generate a random passcode
        String passcode = EmailService.generatePassword("0123456789", 6);
        
        // Send the passcode to the user's email
        EmailService emailService = new EmailService();
        String subject = "Your Passcode Confirmation";
        String body = "Your passcode confirmation is: " + passcode;
        emailService.sendEmail(email, subject, body);
        
        // Store passcode in session for validation later
        request.getSession().setAttribute("passcode", passcode);
        
        // Forward to verification page
        request.getRequestDispatcher("./view/user/verify.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String sessionPasscode = (String) request.getSession().getAttribute("passcode");
            String enteredPasscode = request.getParameter("passconfirm");
            
            // Validate the passcode
            if (sessionPasscode == null) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Passcode is missing. Please try again.");
                return;
            }
            
            if (enteredPasscode == null || !enteredPasscode.equals(sessionPasscode)) {
                request.setAttribute("err", "Passcode does not match. Please try again.");
                request.getRequestDispatcher("./view/user/verify.jsp").forward(request, response);
                return;
            }
            
            // Passcode is correct, update status and redirect to registration page
            request.getSession().setAttribute("status", "confirm");
            response.sendRedirect("register"); // Redirect to register page
            
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred. Please try again later.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles user account verification via email passcode.";
    }
}
