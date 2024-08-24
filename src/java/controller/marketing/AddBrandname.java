/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.marketing;

import controller.user.BaseRequiredAuthenticationController;
import dal.BrandDao;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
public class AddBrandname extends BaseRequiredAuthenticationController {
   
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
        String brandName = request.getParameter("name");
        String description = request.getParameter("description");
        BrandDao brandDao = new BrandDao();
        boolean success = false;
        try {
            brandDao.insertBrandname(brandName, description);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Set a success or failure message
        if (success) {
            request.setAttribute("message", "Brand inserted successfully!");
        } else {
            request.setAttribute("message", "Failed to insert brand. Please try again.");
        }
        request.getRequestDispatcher("/view/marketing/addBandname.jsp").forward(request, response);

    } 


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }

}

