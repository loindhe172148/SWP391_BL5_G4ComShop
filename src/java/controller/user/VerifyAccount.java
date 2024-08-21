package controller.user;

import dal.AccountDBContext;
import dal.UserDBContext;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VerifyAccount extends HttpServlet {

    private Map<String, String> verificationCodes;

    @Override
    public void init() throws ServletException {
        // Initialize the verification codes map
        verificationCodes = (HashMap<String, String>) getServletContext().getAttribute("verificationCodes");
        if (verificationCodes == null) {
            verificationCodes = new HashMap<>();
            getServletContext().setAttribute("verificationCodes", verificationCodes);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve the user input
            String email = request.getParameter("email");
            String code = request.getParameter("code");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            String phone = request.getParameter("phone");
            int gender = Integer.parseInt(request.getParameter("gender"));
            String dobString = request.getParameter("dob");
            String address = request.getParameter("address");
            String fullname = request.getParameter("fullname");

            // Validate input
            if (email == null || code == null || username == null || password == null
                    || role == null || phone == null || dobString == null || address == null || fullname == null) {
                request.setAttribute("errorVerify", "All fields are required.");
                request.getRequestDispatcher("/productHome").forward(request, response);
                return;
            }

            // Parse date of birth
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date dob;
            java.util.Date utilDate = dateFormat.parse(dobString);
            dob = new java.sql.Date(utilDate.getTime());

            request.setAttribute("fullname", fullname);
            request.setAttribute("email", email);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("dob", dob);
            request.setAttribute("gender", gender);
            request.setAttribute("role", role);

            String expectedCode = verificationCodes.get(email);
            if (expectedCode != null && expectedCode.equals(code)) {
                // Verification successful, proceed with registration
                verificationCodes.remove(email);

                request.setAttribute("code", null);  // Clear the verification code from the request

                AccountDBContext accDB = new AccountDBContext();
                Account newAcc = new Account();
                newAcc.setUsername(username);
                newAcc.setPassword(password);
                newAcc.setRole(role);
                accDB.insert(newAcc);

                int accId = accDB.getAccountIDByUsername(username);
                UserDBContext userDB = new UserDBContext();
                userDB.insert(accId, email, address, gender, phone, dob, "Active", "", fullname, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
                request.setAttribute("verificationSuccess", true);
                request.getRequestDispatcher("/productHome").forward(request, response);
            } else {
                // Verification failed
                request.setAttribute("errorVerify", "Invalid verification code. Please try again.");
                request.getRequestDispatcher("/productHome").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(VerifyAccount.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorVerify", "An unexpected error occurred. Please try again.");
            request.getRequestDispatcher("/productHome").forward(request, response);
        }
    }
}
