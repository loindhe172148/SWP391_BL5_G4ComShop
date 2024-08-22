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
public class ProductHome extends HttpServlet {
   
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
    
    // Default values
    int page = 1;
    int pageSize = 6; // Number of products per page
    
    // Retrieve the current page from the request, if available
    if (request.getParameter("page") != null) {
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 1; // If the parameter is not a number, default to page 1
        }
    }

    // Ensure that page is greater than 0
    if (page < 1) {
        page = 1;
    }

    // Calculate the total number of products and pages
    int totalProducts = productDAO.getTotalProducts();
    int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

    // Fetch the products for the current page
    List<Product> productList = productDAO.getProductsByPage(page, pageSize);

    // Set attributes for the JSP
    request.setAttribute("productList", productList);
    request.setAttribute("currentPage", page);
    request.setAttribute("totalPages", totalPages);

    // Fetch and set additional product data
    List<Product> productListNew = productDAO.getNewestProducts(5);
    List<Product> topDiscountedProducts = productDAO.getTopDiscountedProducts(5);
    List<Product> topDiscountedProductsA = productDAO.getTopDiscountedProducts(3);

    request.setAttribute("productListnew", productListNew);
    request.setAttribute("topDiscountedProducts", topDiscountedProducts);
    request.setAttribute("topDiscountedProductsa", topDiscountedProductsA);

    // Fetch and set brand and category data
    BrandDao brandDao = new BrandDao();
    List<Brandname> brandList = brandDao.getBrandnameAccessory();
    request.setAttribute("listB", brandList);

    CategoryDAO categoryDAO = new CategoryDAO();
    List<Category> categoryList = categoryDAO.getAllCategory();
    request.setAttribute("listC", categoryList);

    // Forward the request to the JSP page
    request.getRequestDispatcher("/ProductHome.jsp").forward(request, response);
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
