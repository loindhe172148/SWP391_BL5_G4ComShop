/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.marketing;

import dal.RAMDAO;
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
public class AddRam extends HttpServlet {
   
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
    String memoryStr = request.getParameter("memory");
    String speedStr = request.getParameter("speed");
    String description = request.getParameter("description");
    
    // Convert memory and speed parameters to integers
    int memory = 0;
    int speed = 0;
    try {
        memory = Integer.parseInt(memoryStr);
        speed = Integer.parseInt(speedStr);
    } catch (NumberFormatException e) {
        request.setAttribute("message", "Memory and Speed must be valid numbers.");
        request.getRequestDispatcher("/view/marketing/addRAM.jsp").forward(request, response);
        return;
    }
    
    // Create an instance of RAMDAO
    RAMDAO ramDao = new RAMDAO();
    boolean success = false;
    
    try {
        // Insert the new RAM using the DAO method
        ramDao.insertRam(name, brand, memory, speed, description);
        success = true;
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    // Set a success or failure message
    if (success) {
        request.setAttribute("message", "RAM inserted successfully!");
    } else {
        request.setAttribute("message", "Failed to insert RAM. Please try again.");
    }
    
    // Forward to the appropriate JSP page
    request.getRequestDispatcher("/view/marketing/addRAM.jsp").forward(request, response);
}



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
