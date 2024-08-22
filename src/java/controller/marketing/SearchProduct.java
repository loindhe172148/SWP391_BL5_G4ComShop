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
public class SearchProduct extends HttpServlet {
   
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

    String idB = request.getParameter("idBrandName");
    String searchQuery = request.getParameter("search"); // Lấy từ khóa tìm kiếm từ yêu cầu
    System.out.println("Received idBrandName: " + idB); // In giá trị idBrandName để kiểm tra
    System.out.println("Received search query: " + searchQuery); // In từ khóa tìm kiếm để kiểm tra

    ProductDAO productDAO = new ProductDAO();
    BrandDao brandDao = new BrandDao();

    // Default values for pagination
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

    List<Product> searchProducts;
    int totalProducts;
    int totalPages;
    
    // Xử lý tìm kiếm sản phẩm nếu có từ khóa tìm kiếm
    if (searchQuery != null && !searchQuery.trim().isEmpty()) {
        // Lấy tổng số sản phẩm tìm kiếm và tính số trang
        totalProducts = productDAO.getTotalSearchProducts(searchQuery);
        totalPages = (int) Math.ceil((double) totalProducts / pageSize);
        
        // Lấy danh sách sản phẩm tìm kiếm cho trang hiện tại
        searchProducts = productDAO.getSearchProducts(searchQuery, page, pageSize);
    } else if (idB != null && !idB.isEmpty()) {
        // Lấy tổng số sản phẩm lọc và tính số trang
        totalProducts = productDAO.getTotalFilteredProducts(idB);
        totalPages = (int) Math.ceil((double) totalProducts / pageSize);
        
        // Lấy danh sách sản phẩm đã lọc cho trang hiện tại
        searchProducts = productDAO.getFilterProducts(idB, page, pageSize);
    } else {
        // Lấy tổng số sản phẩm và tính số trang
        totalProducts = productDAO.getTotalProducts();
        totalPages = (int) Math.ceil((double) totalProducts / pageSize);
        
        // Lấy tất cả sản phẩm cho trang hiện tại
        searchProducts = productDAO.getProductsByPage(page, pageSize);
    }
    
    // Thiết lập các thuộc tính cho JSP
    request.setAttribute("productList", searchProducts);
    request.setAttribute("currentPage", page);
    request.setAttribute("totalPages", totalPages);

    // Lấy danh sách thương hiệu và danh mục
    List<Brandname> brandList = brandDao.getBrandnameAccessory();
    request.setAttribute("listB", brandList);

    List<Product> productListNew = productDAO.getNewestProducts(5);
    List<Product> topDiscountedProducts = productDAO.getTopDiscountedProducts(5);
    List<Product> topDiscountedProductsA = productDAO.getTopDiscountedProducts(3);

    request.setAttribute("productListnew", productListNew);
    request.setAttribute("topDiscountedProducts", topDiscountedProducts);
    request.setAttribute("topDiscountedProductsa", topDiscountedProductsA);

    CategoryDAO categoryDAO = new CategoryDAO();
    List<Category> categoryList = categoryDAO.getAllCategory();
    request.setAttribute("listC", categoryList);

    // Forward đến JSP để hiển thị dữ liệu
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
