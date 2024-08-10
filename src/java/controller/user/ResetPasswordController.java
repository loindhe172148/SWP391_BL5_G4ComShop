package controller.user;

import dal.AccountDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Handles password reset requests.
 */
@WebServlet(name = "ResetPasswordController", urlPatterns = {"/reset-password"})
public class ResetPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the password reset page
        request.getRequestDispatcher("WEB-INF/view/resetpass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AccountDBContext adc = new AccountDBContext();
            
            // Retrieve the username and new password from the request
            String username = (String) request.getSession().getAttribute("username");
            String password = request.getParameter("pass");
            
            // Check if username and password are provided
            if (username != null && password != null && !password.trim().isEmpty()) {
                // Update the password in the database
                adc.updatePasswordByUsername(password, username);

                // Invalidate the session to log out the user
                request.getSession().invalidate();
                
                // Set success message
                request.setAttribute("reset_success", "Password reset successfully. You can now log in with your new password.");
                
                // Forward to the login page
                request.getRequestDispatcher("WEB-INF/view/user/login.jsp").forward(request, response);
            } else {
                // Handle the case where username or password is not provided
                request.setAttribute("error", "Username or password is missing.");
                request.getRequestDispatcher("WEB-INF/view/resetpass.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while resetting the password. Please try again later.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles password reset requests.";
    }
}
