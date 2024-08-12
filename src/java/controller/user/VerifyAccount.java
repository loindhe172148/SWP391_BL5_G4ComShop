package controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import util.EmailService;

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
        String str = "0123456789";
        String passcode = EmailService.generatePassword(str, 6);
        
        // Send the passcode to the user's email
        EmailService emailService = new EmailService();
        emailService.sendEmail(email, "Your passcode confirmation is: " + passcode, "Your passcode confirmation is: " + passcode);
        
        // Store passcode in session for validation later
        request.getSession().setAttribute("passcode", passcode);
        
        // Forward to verification page
        request.getRequestDispatcher("./view/user/verify.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String passcode = (String) request.getSession().getAttribute("passcode");
            String passconfirm = request.getParameter("passconfirm");
            
            if (passcode == null) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Passcode is missing. Please try again.");
                return;
            }
            
            if (!passconfirm.equals(passcode)) {
                request.setAttribute("err", "Passcode doesn't match!");
                request.getRequestDispatcher("./view/user/verify.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("status", "confirm");
                response.sendRedirect("login"); // Redirect to register page
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred. Please try again later!");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for verifying user accounts via email passcode.";
    }
}