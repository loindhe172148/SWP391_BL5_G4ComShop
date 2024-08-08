package controller.user;

import dal.AccountDBContext;
import dal.UserDBContext;
import entity.Account;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@MultipartConfig
public class ProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/user/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AccountDBContext adc = new AccountDBContext();
            UserDBContext udc = new UserDBContext();
            
            Part filePart = request.getPart("avatar");
            Account account = (Account) request.getSession().getAttribute("account");
            User user = (User) request.getSession().getAttribute("user");
            
            String username = request.getParameter("username");
            String fullname = request.getParameter("name");
            String gender = request.getParameter("gender");
            String dob = request.getParameter("dob");
            String address = request.getParameter("add");

            // Update Account Information
            Account updateAcc = new Account();
            updateAcc.setUsername(username);
            adc.updateById(updateAcc, account.getId());

            // Prepare User for Update
            User updateUser = new User();
            updateUser.setName(fullname);
            updateUser.setGender("Male".equals(gender));
            updateUser.setBod(java.sql.Date.valueOf(dob));
            updateUser.setAddress(address);
            updateUser.setAccount(account);

            // Handle Avatar File Upload
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String fileType = filePart.getContentType();
                
                if ("image/jpeg".equals(fileType) || "image/png".equals(fileType)) {
                    String uploadDir = getServletContext().getRealPath("/resources/uploads");
                    File uploadDirFile = new File(uploadDir);
                    if (!uploadDirFile.exists()) {
                        uploadDirFile.mkdirs();
                    }
                    Path filePath = Paths.get(uploadDir, fileName);
                    Files.copy(filePart.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    updateUser.setAva(fileName);
                } else {
                    String initialImage = request.getParameter("image-initiate");
                    updateUser.setAva(initialImage);
                }
            } else {
                String initialImage = request.getParameter("image-initiate");
                updateUser.setAva(initialImage);
            }

            // Update User Information
            udc.updateById(updateUser, user.getId());
            request.getSession().setAttribute("user", updateUser);
            
            // Forward to Profile Page
            request.getRequestDispatcher("WEB-INF/view/user/profile.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred. Please try again later!");
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles profile update requests.";
    }
}
