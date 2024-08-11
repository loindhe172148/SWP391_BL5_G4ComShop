package controller.user;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class DefaultPassController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the default password view
        request.getRequestDispatcher("./view/user/defaultpass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the default password from the session
        String defaultPassword = (String) request.getSession().getAttribute("defaultPass");
        // Retrieve the password entered by the user
        String verifyPass = request.getParameter("de-pass");

        // Check if the entered password matches the default password
        if (defaultPassword == null) {
            // If no default password is set, set an error message and forward to the view
            request.setAttribute("err", "Default password is not set. Please contact support.");
            request.getRequestDispatcher("./view/user/defaultpass.jsp").forward(request, response);
            return;
        }

        if (verifyPass == null || !verifyPass.equals(defaultPassword)) {
            // If the password is incorrect, set an error message and forward to the view
            request.setAttribute("err", "Default password isn't correct. Please try again!");
            request.getRequestDispatcher("./view/user/defaultpass.jsp").forward(request, response);
        } else {
            // If the password is correct, forward to the reset password view
            request.getRequestDispatcher("./view/user/resetpass.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for verifying default password and redirecting accordingly.";
    }
}