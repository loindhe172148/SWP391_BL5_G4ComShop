package controller.user;

import dal.AccountDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entity.Account;

/**
 *
 * @author Admin
 */
public class ChangePasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/user/changepassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDBContext adc = new AccountDBContext();
        String username = request.getParameter("username");
        String oldPassword = request.getParameter("old-password");
        String newPassword = request.getParameter("pass");

        // Kiểm tra dữ liệu đầu vào
        if (username == null || oldPassword == null || newPassword == null ||
            username.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty()) {
            request.setAttribute("err", "All fields are required.");
            request.getRequestDispatcher("WEB-INF/view/user/changepassword.jsp").forward(request, response);
            return;
        }

        // Xử lý thay đổi mật khẩu
        try {
            Account account = adc.getAccount(username, oldPassword);
            if (account == null) {
                request.setAttribute("err", "Invalid username or old password.");
            } else {
                adc.updatePasswordByUsername(newPassword, username);
                request.setAttribute("success", "Password changed successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "An error occurred while changing the password.");
        }

        request.getRequestDispatcher("WEB-INF/view/user/profile.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Change Password Servlet";
    }
}
