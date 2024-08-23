/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import controller.user.BaseRequiredAuthenticationController;
import dal.AccountDBContext;
import dal.UserDBContext;
import entity.Account;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.EmailUtils;

/**
 *
 * @author hbtth
 */
public class AddNewUserController extends BaseRequiredAuthenticationController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddNewUserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewUserController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String dob = req.getParameter("dob");
        String gender = req.getParameter("gender");
        String role = req.getParameter("role");
        User user = new User();
        Account a = new Account();
        a.setUsername(username);
        a.setPassword(password);
        a.setRole(role);
        user.setAccount(account);
        user.setName(fullname);
        user.setGmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate;
        try {
            utilDate = dateFormat.parse(dob);
            user.setdob(utilDate);
        } catch (ParseException ex) {
            Logger.getLogger(AddNewUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        user.setGender(Integer.parseInt(gender));
        AccountDBContext adc = new AccountDBContext();
        Account existingAccount = adc.checkAccountExist(username);
        if (existingAccount != null) {
            req.setAttribute("errorSignup", "Username already exists.");
            forwardToSignup(req, resp);
            return;
        }

        if (username.length() <= 3) {
            req.setAttribute("errorSignup", "Username must be more than 3 characters.");
            forwardToSignup(req, resp);
            return;
        }

        if (username.contains(" ")) {
            req.setAttribute("errorSignup", "Username must not contain spaces.");
            forwardToSignup(req, resp);
            return;
        }

        if (password == null || password.length() < 8) {
            req.setAttribute("errorSignup", "Password must not be less than 8 characters.");
            forwardToSignup(req, resp);
            return;
        }

        if (password.contains(" ")) {
            req.setAttribute("errorSignup", "Password must not contain spaces.");
            forwardToSignup(req, resp);
            return;
        }

        if (!isValidEmail(email)) {
            req.setAttribute("errorSignup", "Invalid email format.");
            forwardToSignup(req, resp);
            return;
        }

        if (phone == null || phone.length() != 10 || !phone.matches("\\d+")) {
            req.setAttribute("errorSignup", "Phone number must be 10 digits long and contain only numbers.");
            forwardToSignup(req, resp);
            return;
        }

        if (address != null && address.length() > 50) {
            req.setAttribute("errorSignup", "Address cannot exceed 50 characters.");
            forwardToSignup(req, resp);
            return;
        }
        adc.insert(a);
        a.setId(adc.getAccountIDByUsername(username));
        UserDBContext userDB = new UserDBContext();
        userDB.createUser(user);
        req.setAttribute("successMessage", "Add successful.");
        sendMail(user);
        forwardToSignup(req, resp);
    }

    private void sendMail(User u){
        StringBuilder message = new StringBuilder();
        message.append("Dear ").append(u.getGender() == 1 ? "Mr. " : "Ms. ").append(u.getName());
        message.append("\nThank for join with us. Here is information Account about position ").append(u.getAccount().getRole());
        message.append(" of you.").append("Please login and change your password\n");
        message.append("Username: ").append(u.getAccount().getUsername()).append("\n");
        message.append("Password: ").append(u.getAccount().getPassword()).append("\n");
        message.append("Thank you!!!!");
        String subject = "Information about account for employee";
        boolean emailSent = EmailUtils.sendMail(u.getGmail(), subject, message.toString());              
    }
    private void forwardToSignup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/admin/AddUserAdmin.jsp").forward(request, response);
    }

     private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        req.getRequestDispatcher("/view/admin/AddUserAdmin.jsp").forward(req, resp);
    }

}
