/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
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
        Account account = adc.getAccount(username, oldPassword);
        
        if (account == null) {
            request.setAttribute("err", "Unable to change password. Please try again.");
        } else {
            adc.updatePasswordByUsername(newPassword, username);
            request.setAttribute("success", "Password changed successfully!");
        }
        request.getRequestDispatcher("WEB-INF/view/user/profile.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Change Password Servlet";
    }
}