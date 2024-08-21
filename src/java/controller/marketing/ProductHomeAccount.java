/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.marketing;

import dal.BrandDao;
import dal.CategoryDAO;
import dal.ProductDAO;
import entity.Brandname;
import entity.Category;
import entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ProductHomeAccount extends HttpServlet {
   
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
    
  ProductDAO productDAO = new ProductDAO();
      
    List<Product> productList = productDAO.getAllProduct();
    List<Product> productListnew = productDAO.getNewestProducts(5);
    List<Product> topDiscountedProducts = productDAO.getTopDiscountedProducts(5);
    List<Product> topDiscountedProductsa = productDAO.getTopDiscountedProducts(3);
    
    

    request.setAttribute("productList", productList);
    request.setAttribute("productListnew", productListnew);
    request.setAttribute("topDiscountedProducts", topDiscountedProducts);
    request.setAttribute("topDiscountedProductsa", topDiscountedProductsa);
    BrandDao band = new BrandDao();
        List<Brandname> listB = band.getBrandnameAccessory();
        request.setAttribute("listB", listB);
         CategoryDAO category = new CategoryDAO();
         List<Category> listC = category.getAllCategory();
             request.setAttribute("listC", listC);

        request.getRequestDispatcher("/ProductHomeAccount.jsp").forward(request, response);
       
        
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
