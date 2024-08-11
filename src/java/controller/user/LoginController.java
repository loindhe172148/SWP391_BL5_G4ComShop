package controller.user;

import dal.AccountDBContext;
import dal.UserDBContext;
import entity.Account;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve cookies
        Cookie[] cookies = request.getCookies();
        String username = "", rememberMe = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
                if (cookie.getName().equals("rememberMe")) {
                    rememberMe = cookie.getValue();
                }
            }
        }

        // Set attributes for the login form
        request.setAttribute("username", username);
        request.setAttribute("rememberMe", rememberMe);
        request.getRequestDispatcher("./view/user/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve user credentials from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        AccountDBContext accountDB = new AccountDBContext();
        // Verify user credentials
        Account account = accountDB.getAccount(username, password);

        if (account == null) {
            // Invalid credentials
            request.setAttribute("error", "Invalid username or password. Please try again.");
            request.getRequestDispatcher("./view/user/login.jsp").forward(request, response);
        } else {
            // Valid credentials
            // Set cookies if "Remember Me" is checked
            Cookie c_user = new Cookie("username", username);
            Cookie c_remember = new Cookie("rememberMe", rememberMe);

            int maxAge = (rememberMe != null && rememberMe.equals("on")) ? 3600 * 24 * 7 : 0; // 7 days or 0 for session only
            c_user.setMaxAge(maxAge);
            c_remember.setMaxAge(maxAge);

            response.addCookie(c_user);
            response.addCookie(c_remember);

            // Set user information in session
            UserDBContext userDB = new UserDBContext();
            User user = userDB.getUserById(account.getId());
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("account", account);

            response.sendRedirect("home");
        }
    }
}