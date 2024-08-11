/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DaoAccessory;
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
public class AddAccessory extends HttpServlet {
   
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
        
        String aname = request.getParameter("name");
        String abandname = request.getParameter("bandname");
        String astatus = request.getParameter("status");
        String adescription = request.getParameter("description");
        String acapacity = request.getParameter("capacity");
        String aconnect = request.getParameter("connect");
        String acompatible = request.getParameter("compatible");
        String acolor = request.getParameter("color");
        String adpi = request.getParameter("dpi");
        String alayout = request.getParameter("layout");
        String aswitch = request.getParameter("switcha");
        String afeature = request.getParameter("feature");
        String aoriginprice = request.getParameter("originprice");
        String asaleprice = request.getParameter("saleprice");
        String aquantity = request.getParameter("quantity");
        String aimg = request.getParameter("img");
        DaoAccessory daoAccessory = new DaoAccessory();
        daoAccessory.insertAccessory(aname, abandname, astatus, adescription, acapacity, aconnect, acompatible, acolor, adpi, alayout, aswitch, afeature, aoriginprice, asaleprice, aquantity, aimg);
        response.sendRedirect("Accessory");

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
