/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.marketing;

import dal.BrandDao;
import dal.CPUDAO;
import dal.CardDAO;
import dal.ProductDAO;
import dal.RAMDAO;
import dal.SupplierDao;
import entity.Brandname;
import entity.CPU;
import entity.Card;
import entity.Product;
import entity.RAM;
import entity.Suppliere;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class Supplier extends HttpServlet {
   
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
    
    // Assuming you have already established a connection to the database.
    ProductDAO productDAO = new ProductDAO();
    List<Product> productALL = productDAO.getAll();
    request.setAttribute("productALL", productALL);

    // Assuming you have DAOs for other entities
    SupplierDao supplierDao = new SupplierDao();
    List<Suppliere> suppliers = supplierDao.getAllSuppliers();
    request.setAttribute("suppliers", suppliers);

    BrandDao brandDao = new BrandDao();
    List<Brandname> listB = brandDao.getBrandnameAccessory();
    request.setAttribute("listB", listB);

    CardDAO cardDAO = new CardDAO();
    List<Card> listCa = cardDAO.getCardAccessory();
    request.setAttribute("listCa", listCa);

    CPUDAO cpuDAO = new CPUDAO();
    List<CPU> listC = cpuDAO.getCpuAccessory();
    request.setAttribute("listC", listC);

    RAMDAO ramDAO = new RAMDAO();
    List<RAM> listR = ramDAO.getRamAccessory();
    request.setAttribute("listR", listR);
    
    // Forward to the JSP page
    request.getRequestDispatcher("/view/marketing/Supplier.jsp").forward(request, response);
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
