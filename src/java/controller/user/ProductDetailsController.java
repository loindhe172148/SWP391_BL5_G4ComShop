package controller.user;

import dal.ProductWithDetailsDAO;
import dal.ROMDAO;
import entity.ProductWithDetails;
import entity.ROM;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductDetailsController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProductWithDetailsDAO productDetailsDAO = new ProductWithDetailsDAO();
        ROMDAO romDAO = new ROMDAO();
        try (PrintWriter out = response.getWriter()) {
            String detailIdParam = request.getParameter("detailId");
            if (detailIdParam == null || detailIdParam.isEmpty()) {
                detailIdParam = "6"; // giả sử người dùng chọn sản phẩm có id là 3
            }
            try {
                int detailId = Integer.parseInt(detailIdParam);

                // Fetch product details from DAO
                ProductWithDetails productWithDetails = productDetailsDAO.getProductDetailById(detailId);
                List<String> colorList = productDetailsDAO.getDistinctColorsByProductId(detailId);

                // Lấy danh sách ROMs dựa trên productId
                List<ROM> romList = romDAO.getROMsByProductId(detailId);

                if (productWithDetails != null) {
                    // Set the product details and romList as request attributes
                    request.setAttribute("productWithDetails", productWithDetails);
                    request.setAttribute("colorList", colorList);
                    request.setAttribute("romList", romList);

                    // Forward to JSP page to display the product details
                    request.getRequestDispatcher("/view/customer/ProductDetails.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product detail ID format");
            }
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

}
