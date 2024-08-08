/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user.;

import dal.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import util.Util;

/**
 *
 * @author Admin
 */
public class ForgotAccountController extends HttpServlet {

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
            out.println("<title>Servlet ForgotAccountController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotAccountController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("WEB-INF/view/forgotpassword.jsp").forward(request, response);
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
        AccountDBContext adc = new AccountDBContext();
        try {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            HttpSession session = request.getSession();
            Account a = adc.getAccountByUsernameAndEmail(username, email);
            if(a == null){
                request.setAttribute("err", "Unable to find your account! Please try again");
                request.getRequestDispatcher("WEB-INF/view/forgotpassword.jsp").forward(request, response);
            }else{
                String defaultPassword = (String) session.getAttribute("defaultPass");
                if(defaultPassword != null){
                    request.setAttribute("err", "Default pass is sent to your email. Please check your mail and click <a href = 'defaultpass'>here</a> to enter");
                    request.getRequestDispatcher("WEB-INF/view/forgotpassword.jsp").forward(request, response);
                    return;
                }
                String str = "abcdefgfijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*";
                String defaultPass = Util.generatePassword(str, 8);
                String text = "Your default password is: " + defaultPass + ".Default password has been set in the session and session timeout is set to 1 minute.";
                Util.sendEmail(email, text);
                session.setAttribute("defaultPass", defaultPass);
                session.setMaxInactiveInterval(60);
                session.setAttribute("email", email);
                session.setAttribute("username", username);
                request.getRequestDispatcher("WEB-INF/view/defaultpass.jsp").forward(request, response);
            }
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
