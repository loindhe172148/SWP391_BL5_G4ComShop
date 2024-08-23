package controller.marketing;

import controller.user.BaseRequiredAuthenticationController;
import dal.ProductDAO;
import dal.ProductWithDetailsDAO;
import entity.ProductWithDetails;
import entity.Account;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class MarketingProductList extends BaseRequiredAuthenticationController {
    
    private static final int PAGE_SIZE = 5;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String servletPath = request.getServletPath();
        String search = request.getParameter("search");
        String sortColumn = request.getParameter("sortColumn");
        String sortOrder = request.getParameter("sortOrder");
        String pageStr = request.getParameter("page");
        String searchValue = search;
        if (search == null) {
            searchValue = "";
        } else {
            if (search.trim().equals("_")) {
                searchValue = search.replace("_", "[_]").trim();
            }
            if (search.trim().equals("%")) {
                searchValue = search.replace("%", "[%]").trim();
            }
        }
        
        if (sortOrder == null || sortOrder.isEmpty()) {
            sortOrder = "asc"; // Default sorting order
        }
        int page;
        try {
            page = Integer.parseInt(pageStr);
        } catch (NumberFormatException e) {
            page = 1;
        }
        
        int start = (page - 1) * PAGE_SIZE;
        switch (servletPath) {
            case "/marketing/productlist":
                
                ProductWithDetailsDAO productDAO = new ProductWithDetailsDAO();
                try {
                    List<ProductWithDetails> products = productDAO.getProductWithDetails(start, PAGE_SIZE, searchValue, sortColumn, sortOrder);
                    int totalProducts = productDAO.getProductCount(searchValue);
                    int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);
                    
                    request.setAttribute("products", products);
                    request.setAttribute("totalPages", totalPages);
                    request.setAttribute("currentPage", page);
                    request.setAttribute("search", search);
                    request.setAttribute("sortColumn", sortColumn);
                    request.setAttribute("sortOrder", sortOrder);
                    request.getRequestDispatcher("/view/marketing/ProductList.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching product data.");
                }
                break;
            case "/marketing/changestatus":
                String id = request.getParameter("id");
                String currentStatus = request.getParameter("currentStatus");
                if (id != null && !id.isEmpty()) {
                    String newStatus = "Showing".equals(currentStatus) ? "Hiding" : "Showing";
                    ProductDAO product = new ProductDAO();
                    try {
                        product.updateProductStatus(Integer.parseInt(id), newStatus);
                        response.sendRedirect("productlist?page=" + page + "&sortColumn=" + sortColumn + "&sortOrder=" + sortOrder + "&search=" + search);
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating product status.");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID.");
                }
                break;
            
            default:
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, servletPath + " khong ton tai");
            
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
        processRequest(request, response);
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
        processRequest(request, response);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
}
