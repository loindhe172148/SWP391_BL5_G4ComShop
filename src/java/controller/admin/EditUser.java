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

/**
 *
 * @author hbtth
 */
public class EditUser extends BaseRequiredAuthenticationController {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet EditUser</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditUser at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

  
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String status = req.getParameter("status");
        String role = req.getParameter("role");
        UserDBContext db = new UserDBContext();
        AccountDBContext dba = new AccountDBContext();
        System.out.println(username);
        System.out.println(id);
        System.out.println(role);
        System.out.println(status);
        db.updateStatusByID(Integer.parseInt(id), status);
        dba.updateRoleByUsername(username, role);
        System.out.println("");
        resp.sendRedirect("edituser?userId="+id);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        UserDBContext dao = new UserDBContext();
        User user = dao.getUserByID(Integer.parseInt(userId));
        req.setAttribute("user1", user);
        req.getRequestDispatcher("/view/admin/EditUserAdmin.jsp").forward(req, resp);
    }

}
