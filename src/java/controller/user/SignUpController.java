package controller.user;

import dal.AccountDBContext;
import dal.UserDBContext;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
        HttpSession session = request.getSession();
        AccountDBContext adc = new AccountDBContext();
        AccountDBContext adc1 = new AccountDBContext();
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
                String code = generateVerificationCode();
                verificationCodes.put(email, code);

                // Check if account already exists
                Account existingAccount = adc.checkAccountExist(username, role);
                if (existingAccount != null) {
                    request.setAttribute("err", "Username or email already exists. Please try again!");
                    request.getRequestDispatcher("./view/user/signup.jsp").forward(request, response);
                    return;
                }


                emailService.sendEmail(email, "Account Verification",
                    """
                            Dear user,
                        Thank you for registering. Here is your verify code:
                                    
                                    """ + code + "\n");
                
                // Clean up session and redirect to login page
                request.getRequestDispatcher("./view/user/verify.jsp").forward(request, response);
                
                request.setAttribute("fullname", fullname);
                request.setAttribute("email", email);
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
                request.setAttribute("dob", dob);
                request.setAttribute("gender", gender);
                request.setAttribute("role", role);
                request.setAttribute("code", code);
            }
        } catch (Exception e) { // Log the exception
            // Log the exception
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request. Please try again later.");
        }
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
}
