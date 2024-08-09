package controller.user;

import dal.AccountDBContext;
import dal.UserDBContext;
import entity.Account;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SignUpController", urlPatterns = {"/signup"})
public class SignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the signup page
        request.getRequestDispatcher("WEB-INF/view/user/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountDBContext adc = new AccountDBContext();
        UserDBContext udc = new UserDBContext();
        
        try {
            String status = (String) session.getAttribute("status");

            if (status == null) {
                // Collecting form data
                String fullname = request.getParameter("name");
                String email = request.getParameter("email");
                String username = request.getParameter("username");
                String password = request.getParameter("pass");
                String phone = request.getParameter("phone");
                String address = request.getParameter("add");
                String dob = request.getParameter("dob");
                String gender = request.getParameter("gender");
                String role = request.getParameter("role");

                // Check if account already exists
                Account existingAccount = adc.checkAccountExist(username, email);
                if (existingAccount != null) {
                    request.setAttribute("err", "Username or email already exists. Please try again!");
                    request.getRequestDispatcher("WEB-INF/view/user/signup.jsp").forward(request, response);
                    return;
                }

                // Store form data in session
                session.setAttribute("name", fullname);
                session.setAttribute("username", username);
                session.setAttribute("pass", password);
                session.setAttribute("phone", phone);
                session.setAttribute("address", address);
                session.setAttribute("dob", dob);
                session.setAttribute("gender", gender);
                session.setAttribute("role", role);
                session.setAttribute("email", email);

                // Redirect to verification page
                response.sendRedirect("verify");
            } else {
                // Retrieve data from session
                String name = (String) session.getAttribute("name");
                String username = (String) session.getAttribute("username");
                String password = (String) session.getAttribute("pass");
                String phone = (String) session.getAttribute("phone");
                String address = (String) session.getAttribute("address");
                String dob = (String) session.getAttribute("dob");
                String gender = (String) session.getAttribute("gender");
                String email = (String) session.getAttribute("email");
                String role = (String) session.getAttribute("role");

                // Create and save new account
                Account newAcc = new Account();
                newAcc.setUsername(username);
                newAcc.setPassword(password);
                newAcc.setRole(role);
                adc.insert(newAcc);

                // Create and save new user
                User newUser = new User();
                newUser.setName(name);
                newUser.setGender("Male".equals(gender));
                newUser.setPhone(phone);
                newUser.setAddress(address);
                newUser.setBod(java.sql.Date.valueOf(dob));
                newUser.setAccount(newAcc);
                udc.insert(newUser);

                // Clean up session and redirect to login page
                session.removeAttribute("status");
                request.setAttribute("success", "Sign up successfully! You can log in to our system.");
                request.getRequestDispatcher("WEB-INF/view/user/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request. Please try again later.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles user sign up requests.";
    }
}
