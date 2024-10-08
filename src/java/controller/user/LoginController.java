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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        AccountDBContext accountDB = new AccountDBContext();
        Account account = accountDB.getAccount(username, password);

        if (account == null) {
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("errorLogin", "Invalid username or password");
            request.getRequestDispatcher("/productHome").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("account", account);
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("rememberMe", rememberMe);
        session.setAttribute("userRole", account.getRole());

        UserDBContext userDB = new UserDBContext();
        User user = userDB.getUserById(account.getId());
        session.setAttribute("user", user);

        if ("on".equals(rememberMe)) {
            Cookie cuser = new Cookie("username", username);
            Cookie cpass = new Cookie("password", password);
            Cookie cRememberMe = new Cookie("rememberMe", "on");

            cuser.setMaxAge(3600 * 24 * 7);
            cpass.setMaxAge(3600 * 24 * 7);
            cRememberMe.setMaxAge(3600 * 24 * 7);

            response.addCookie(cuser);
            response.addCookie(cpass);
            response.addCookie(cRememberMe);
        } else {
            Cookie cuser = new Cookie("username", "");
            Cookie cpass = new Cookie("password", "");
            Cookie cRememberMe = new Cookie("rememberMe", "");

            cuser.setMaxAge(0);
            cpass.setMaxAge(0);
            cRememberMe.setMaxAge(0);

            response.addCookie(cuser);
            response.addCookie(cpass);
            response.addCookie(cRememberMe);
        }

        String role = account.getRole();
        switch (role) {
            case "customer":
                response.sendRedirect(request.getContextPath() + "/productHome?id=" + account.getId());
                break;
            case "marketing":
                response.sendRedirect(request.getContextPath() + "/marketing/dashboard?id=" + account.getId());
                break;
            case "admin":
                response.sendRedirect(request.getContextPath() + "/admin/adminUserList");
                break;
            case "sale":
                response.sendRedirect(request.getContextPath() + "/sale/dashboard");
                break;
            default:
                session.invalidate();
                request.setAttribute("errorLogin", "Unknown user role.");
                request.getRequestDispatcher("/productHome").forward(request, response);
                break;
        }
    }
}

