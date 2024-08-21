package controller.user;

import dal.AccountDBContext;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseRequiredAuthenticationController extends HttpServlet {

    private Account getAuthentication(HttpServletRequest req) {
        Account account = (Account) req.getSession().getAttribute("account");
        if (account == null) {
            String username = null;
            String password = null;
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cooky : cookies) {
                    if (cooky.getName().equals("username")) {
                        username = cooky.getValue();
                    }
                    if (cooky.getName().equals("password")) {
                        password = cooky.getValue();
                    }
                    if (username != null && password != null) {
                        break;
                    }
                }

                if (username != null && password != null) {
                    AccountDBContext db = new AccountDBContext();
                    return db.getAccount(username, password);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }

        return account;
    }

    private boolean isAuthorized(Account account, String servletPath) {
        if (servletPath != null && servletPath.startsWith("/")) {
            String[] pathParts = servletPath.split("/");
            if (pathParts.length > 1) {
                String firstPart = pathParts[1];
                return firstPart.equalsIgnoreCase(account.getRole()); 
            }
        }
        return false;
    }

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = getAuthentication(req);
        String servletPath = req.getServletPath();
        if (account != null && isAuthorized(account, servletPath)) {
            doPost(req, resp, account);
        } else {
            resp.sendRedirect("/SWP391_BL5_G4ComShop/view/user/login.jsp");
        }
    }

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = getAuthentication(req);
        String servletPath = req.getServletPath();
        if (account != null && isAuthorized(account, servletPath)) {
            doGet(req, resp, account);
        } else {
            resp.sendRedirect("/SWP391_BL5_G4ComShop/view/user/login.jsp");
        }
    }
}
