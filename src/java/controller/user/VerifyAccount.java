package controller.user;

import dal.AccountDBContext;
import dal.UserDBContext;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class VerifyAccount extends HttpServlet {

    private Map<String, String> verificationCodes;

    /**
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        // Initialize the verification codes map
        verificationCodes = (HashMap<String, String>) getServletContext().getAttribute("verificationCodes");
        if (verificationCodes == null) {
            verificationCodes = new HashMap<>();
            getServletContext().setAttribute("verificationCodes", verificationCodes);
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date utilDate = dateFormat.parse(dobString);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            String address = request.getParameter("address");
            String fullname = request.getParameter("fullname");
            String status = "Active";
            String ava = "";
            java.util.Date utilDate1 = Calendar.getInstance().getTime();
            java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
            
            String expectedCode = verificationCodes.get(email);
            if (expectedCode != null && expectedCode.equals(code)) {
                verificationCodes.remove(email);
                request.setAttribute("code", null);
                
                // Perform user registration
                AccountDBContext acc = new AccountDBContext();
                Account newAcc = new Account();
                newAcc.setUsername(username);
                newAcc.setPassword(password);
                newAcc.setRole(role);
                acc.insert(newAcc);

                int accid = acc.getAccountIDByUsername(username);
                UserDBContext user = new UserDBContext();
                user.insert(accid, email, address, gender, phone, sqlDate, status, ava, fullname, sqlDate1);
                request.getRequestDispatcher("/productHome").forward(request, response);
            } else {
                request.setAttribute("errorVerify", "Invalid verification code. Please try again.");
                request.getRequestDispatcher("/productHome").forward(request, response);
            }
        } catch (ParseException ex) {
            Logger.getLogger(VerifyAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
