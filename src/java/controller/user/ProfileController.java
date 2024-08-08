/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.AccountDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import entity.Account;
import entity.User;

/**
 *
 * @author Admin
 */
@MultipartConfig
public class ProfileController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/user/profile.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AccountDBContext adc = new AccountDBContext();
            UserDBContext udc = new UserDBContext();
            
            Part fPart = request.getPart("avatar");
            Account updateAcc = new Account();
            Account account = (Account) request.getSession().getAttribute("account");
            User user = (User) request.getSession().getAttribute("user");
            String username = request.getParameter("username");
            String fullname = request.getParameter("name");
            String gender = request.getParameter("gender");
            String dob = request.getParameter("dob");
            String address = request.getParameter("add");
            updateAcc.setUsername(username);
            
            adc.updateById(updateAcc, account.getId());
            User updateUser = new User();

            if (fPart != null) {
                String fileName = Paths.get(fPart.getSubmittedFileName()).getFileName().toString();
                String fileType = fPart.getContentType();
                if (fileType.equals("image/jpeg") || fileType.equals("image/png")) {
                   String uploadDir = getServletContext().getRealPath("/resources/uploads");
                   File uploadDirFile = new File(uploadDir);
                   if(!uploadDirFile.exists()){
                       uploadDirFile.mkdirs();
                   }
                   String filePath = uploadDir + "\\" + fileName;
                   fPart.write(filePath);
                   updateUser.setAva(fileName);
                }else{
                    String image = request.getParameter("image-initiate");
                     updateUser.setAva(image);
                }
            }
            
            updateUser.setName(fullname);
            updateUser.setGender(gender.equals("Male"));
            updateUser.setDateOfBirth(java.sql.Date.valueOf(dob));
            updateUser.setAddress(address);
            updateUser.setAccount(account);
            udc.updateById(updateUser, user.getId());
            request.getSession().setAttribute("user", updateUser);
             request.getRequestDispatcher("WEB-INF/view/user/profile.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occured. Please try again later!");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
