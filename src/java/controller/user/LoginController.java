package controller.user;

import dal.AccountDBContext;
import dal.UserDBContext;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String username = "", password = "", rememberMe = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
                if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
                if (cookie.getName().equals("rememberMe")) {
                    rememberMe = cookie.getValue();
                }
            }
        }

        request.setAttribute("user", username);
        request.setAttribute("pass", password);
        request.setAttribute("rememberMe", rememberMe);
        request.getRequestDispatcher("WEB-INF/view/user/login.jsp").forward(request, response);
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
            request.setAttribute("error", "Invalid username or password. Please try again");
            request.getRequestDispatcher("WEB-INF/view/user/login.jsp").forward(request, response);
        } else {
            // Valid credentials
            // Set cookies if "Remember Me" is checked
            Cookie c_user = new Cookie("username", username);
            Cookie c_pass = new Cookie("password", password);
            Cookie c_remember = new Cookie("rememberMe", rememberMe);

            int maxAge = (rememberMe != null) ? 3600 * 24 * 7 : 0; // 7 days or 0 for session only
            c_user.setMaxAge(maxAge);
            c_pass.setMaxAge(maxAge);
            c_remember.setMaxAge(maxAge);

            response.addCookie(c_user);
            response.addCookie(c_pass);
            response.addCookie(c_remember);

            // Set user information in session
            UserDBContext userDB = new UserDBContext();
            request.getSession().setAttribute("user", userDB.getUserById(account.getId()));
            request.getSession().setAttribute("account", account);

            response.sendRedirect("home");
        }
    }
}
