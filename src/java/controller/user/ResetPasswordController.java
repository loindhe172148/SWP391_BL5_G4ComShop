package controller.user;

import dal.AccountDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Handles password reset requests.
 */
public class ResetPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the password reset page
        request.getRequestDispatcher("./view/resetpass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDBContext adc = new AccountDBContext();
        
        try {
            // Retrieve the username and new password from the request
            String username = (String) request.getSession().getAttribute("username");
            String password = request.getParameter("pass");
            
            // Check if username and password are provided
            if (username != null && password != null && !password.trim().isEmpty()) {
                // Hash the new password before updating
                String hashedPassword = hashPassword(password);

                // Update the password in the database
                adc.updatePasswordByUsername(hashedPassword, username);

                // Invalidate the session to log out the user
                request.getSession().invalidate();
                
                // Set success message
                request.setAttribute("reset_success", "Password reset successfully. You can now log in with your new password.");
                
                // Forward to the login page
                request.getRequestDispatcher("./view/user/login.jsp").forward(request, response);
            } else {
                // Handle the case where username or password is not provided
                request.setAttribute("error", "Username or password is missing.");
                request.getRequestDispatcher("./view/resetpass.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while resetting the password. Please try again later.");
        }
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Override
    public String getServletInfo() {
        return "Handles password reset requests.";
    }
}
