package controller.user;

import dal.AccountDBContext;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.EmailService;

@WebServlet(name = "SignUpController", urlPatterns = {"/signup"})
public class SignUpController extends HttpServlet {

    private final EmailService emailService = new EmailService();
    private Map<String, String> verificationCodes = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the signup page
        request.getRequestDispatcher("./view/user/signup.jsp").forward(request, response);
    }

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDBContext adc = new AccountDBContext();

        String fullname = request.getParameter("name");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("pass");
        String confirmpassword = request.getParameter("confirmpassword");
        String phone = request.getParameter("phone");
        String address = request.getParameter("add");
        String dob = request.getParameter("dob");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String role = request.getParameter("role");

        // Preserve entered values
        request.setAttribute("fullname", fullname);
        request.setAttribute("email", email);
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("confirmpassword", confirmpassword);
        request.setAttribute("phone", phone);
        request.setAttribute("address", address);
        request.setAttribute("dob", dob);
        request.setAttribute("gender", gender);
        request.setAttribute("role", role);

        // Check if the username already exists
        Account existingAccount = adc.checkAccountExist(username);
        if (existingAccount != null) {
            request.setAttribute("err", "Username already exists.");
            forwardToSignup(request, response);
            return;
        }

        // Validate username length
        if (username.length() <= 3) {
            request.setAttribute("err", "Username must be more than 3 characters.");
            forwardToSignup(request, response);
            return;
        }

        // Validate password length
        if (password == null || password.length() < 8) {
            request.setAttribute("err", "Password must not be less than 8 characters.");
            forwardToSignup(request, response);
            return;
        }

        // Validate password doesn't contain spaces
        if (password.contains(" ")) {
            request.setAttribute("err", "Password must not contain spaces.");
            forwardToSignup(request, response);
            return;
        }

        // Validate password confirmation
        if (!password.equals(confirmpassword)) {
            request.setAttribute("err", "Passwords do not match.");
            forwardToSignup(request, response);
            return;
        }

        // Validate email format
        if (!isValidEmail(email)) {
            request.setAttribute("err", "Invalid email format.");
            forwardToSignup(request, response);
            return;
        }

        // Validate phone number
        if (phone == null || phone.length() != 10 || !phone.matches("\\d+")) {
            request.setAttribute("err", "Phone number must be 10 digits long and contain only numbers.");
            forwardToSignup(request, response);
            return;
        }

        // Validate address length
        if (address != null && address.length() > 50) {
            request.setAttribute("err", "Address cannot exceed 50 characters.");
            forwardToSignup(request, response);
            return;
        }

        // Generate and send verification code
        String code = generateVerificationCode();
        verificationCodes.put(email, code);
        emailService.sendEmail(email, "Account Verification",
                "Dear user,\nThank you for registering. Here is your verification code:\n" + code + "\n");

        // Forward to verification page
        request.setAttribute("code", code);
        request.getRequestDispatcher("./view/user/verify.jsp").forward(request, response);
    }

    private void forwardToSignup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("./view/user/signup.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles user sign up requests.";
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(999999);
        return String.format("%06d", code);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
