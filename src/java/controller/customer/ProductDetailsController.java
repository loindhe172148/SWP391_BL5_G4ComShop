package controller.customer;

import dal.BrandDAL;
import dal.CPUDAO;
import dal.CardDAO;
import dal.ProductWithDetailsDAO;
import dal.RAMDAO;
import entity.Brand;
import entity.CPU;
import entity.Card;
import entity.ProductWithDetails;
import entity.RAM;
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
        try (PrintWriter out = response.getWriter()) {
            ProductWithDetailsDAO productDetailsDAO = new ProductWithDetailsDAO();
            BrandDAL branddao = new BrandDAL();
            RAMDAO ramdao = new RAMDAO();
            CPUDAO cpudao = new CPUDAO();
            CardDAO carddao = new CardDAO();

            String detailIdParam = request.getParameter("id");
            if (detailIdParam == null || detailIdParam.isEmpty()) {
                detailIdParam = "3"; // Default product ID
            }

            try {
                int detailId = Integer.parseInt(detailIdParam);

                ProductWithDetails productWithDetails = productDetailsDAO.getProductDetailById(detailId);
                if (productWithDetails == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                    return;
                }

                Brand brandname = branddao.getBrandById(productWithDetails.getProduct().getBrandId());
                RAM ramname = ramdao.getRAMById(productWithDetails.getProductDetails().getRamId());
                CPU cpuname = cpudao.getCPUById(productWithDetails.getProductDetails().getCpuId());
                Card cardname = carddao.getCardById(productWithDetails.getProductDetails().getCardId());

                int brandid = productWithDetails.getProduct().getBrandId();
                List<ProductWithDetails> relatedProducts = productDetailsDAO.getProductsByBrand(brandid, 4);

                request.setAttribute("productWithDetails", productWithDetails);
                request.setAttribute("brandname", brandname);
                request.setAttribute("ramname", ramname);
                request.setAttribute("cpuname", cpuname);
                request.setAttribute("cardname", cardname);
                request.setAttribute("relatedProducts", relatedProducts);

                // Check for error or success messages
                String errorMessage = (String) request.getAttribute("errorMessage");
                String successMessage = (String) request.getAttribute("successMessage");

                request.setAttribute("errorMessage", errorMessage);
                request.setAttribute("successMessage", successMessage);

                request.getRequestDispatcher("ProductDetails.jsp").forward(request, response);
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

