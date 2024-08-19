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
import jakarta.servlet.http.HttpSession;
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
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                }
                if ("rememberMe".equals(cookie.getName())) {
                    rememberMe = cookie.getValue();
                }
            }
        }

        // Set attributes for the login form
        request.setAttribute("username", username);
        request.setAttribute("rememberMe", rememberMe);
        request.getRequestDispatcher("/view/user/login.jsp").forward(request, response);
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
            request.getRequestDispatcher("/view/user/login.jsp").forward(request, response);
            return;
        }

        // Successful login
        HttpSession session = request.getSession();
        session.setAttribute("account", account);
        session.setAttribute("username", username);
        session.setAttribute("userRole", account.getRole());

        // Set user information in session
        UserDBContext userDB = new UserDBContext();
        User user = userDB.getUserById(account.getId());
        session.setAttribute("user", user);

        // Handle "Remember Me" functionality
        if ("on".equals(rememberMe)) {
            Cookie cuser = new Cookie("username", username);
            Cookie cRememberMe = new Cookie("rememberMe", "on");

            cuser.setMaxAge(3600 * 24 * 7);  // 7 days
            cRememberMe.setMaxAge(3600 * 24 * 7);  // 7 days

            response.addCookie(cuser);
            response.addCookie(cRememberMe);
        } else {
            // Clear cookies if "Remember Me" is not checked
            Cookie cuser = new Cookie("username", "");
            Cookie cRememberMe = new Cookie("rememberMe", "");

            cuser.setMaxAge(0);  // Delete cookie
            cRememberMe.setMaxAge(0);  // Delete cookie

            response.addCookie(cuser);
            response.addCookie(cRememberMe);
        }

        // Redirect based on user role
        String role = account.getRole();
        switch (role) {
            case "user":
                response.sendRedirect(request.getContextPath() + "/home?id=" + account.getId());
                break;
            case "marketing":
                response.sendRedirect(request.getContextPath() + "/marketingDashboard.jsp?id=" + account.getId());
                break;
            case "admin":
            case "sale":
                response.sendRedirect(request.getContextPath() + "/adminDashboard.jsp?id=" + account.getId());
                break;
            default:
                session.invalidate();
                request.setAttribute("error", "Unknown user role.");
                request.getRequestDispatcher("./view/user/login.jsp").forward(request, response);
                break;
        }
    }
}