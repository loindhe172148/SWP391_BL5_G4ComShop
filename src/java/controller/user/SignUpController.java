package controller.user;

import dal.AccountDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import entity.Account;
import entity.User;

public class SignUpController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SignUpController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("./view/user/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountDBContext adc = new AccountDBContext();
        UserDBContext udc = new UserDBContext();
        
        try {
            // Collect form data
            String fullname = request.getParameter("name");
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");
            String role = request.getParameter("role");
            
            // Check if the account already exists
            boolean existingAccount = adc.checkAccountExist(username);
            if (existingAccount != false) {
                request.setAttribute("err", "Username or email already exists. Please try again!");
                request.getRequestDispatcher("./view/user/signup.jsp").forward(request, response);
                return;
            }

            // Create new Account
            Account newAccount = new Account();
            newAccount.setUsername(username);
            newAccount.setPassword(password);
            newAccount.setRole(role);
            adc.insert(newAccount);
            
            // Create new User linked to the Account
            User newUser = new User();
            newUser.setName(fullname);
            newUser.setPhone(phone);
            newUser.setAddress(address);
            newUser.setEmail(email);
            newUser.setDob(java.sql.Date.valueOf(dob));
            newUser.setGender("Male".equals(gender));
            newUser.setAccount(newAccount);
            udc.insert(newUser);

            session.setAttribute("success", "Sign up successfully! You can now log in.");
            response.sendRedirect("login");
            
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred. Please try again later!");
        }
    }

    @Override
    public String getServletInfo() {
        return "Sign Up Controller";
    }
}
