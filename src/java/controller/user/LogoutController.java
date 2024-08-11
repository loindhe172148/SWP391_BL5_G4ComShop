package controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import java.io.IOException;

public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Invalidate the current session to log out the user
        request.getSession().invalidate();

        // Optionally, remove cookies if present
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username") || cookie.getName().equals("rememberMe")) {
                    cookie.setMaxAge(0); // Set cookie age to 0 to delete it
                    response.addCookie(cookie);
                }
            }
        }

        // Redirect to the login page
        response.sendRedirect("login");
    } 
}