/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.marketing;

import controller.user.BaseRequiredAuthenticationController;
import dal.CardDAO;
import dal.RAMDAO;
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
public class AddCard extends BaseRequiredAuthenticationController {
   
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
        
        // Retrieve parameters from the request
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String memory = request.getParameter("memory");
        String chipset = request.getParameter("chipset");
        String description = request.getParameter("description");
        
        // Create an instance of CardDao
         CardDAO cardDao = new CardDAO();
        boolean success = false;
        
        try {
            // Insert the new Card using the Dao method
            cardDao.insertCard(name, brand, memory, chipset, description);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Set a success or failure message
        if (success) {
            request.setAttribute("message", "Card inserted successfully!");
        } else {
            request.setAttribute("message", "Failed to insert Card. Please try again.");
        }
        
        // Forward to the appropriate JSP page
        request.getRequestDispatcher("/view/marketing/addCard.jsp").forward(request, response);
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

