package controller.marketing;

import controller.user.BaseRequiredAuthenticationController;
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

@WebServlet(name = "ProductListController", urlPatterns = {"/marketing/productlist", "/marketing/changestatus"})
public class ProductListController extends HttpServlet {

    private static final int PAGE_SIZE = 10;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }

            String sortColumn = request.getParameter("sortColumn");

            String sortOrder = request.getParameter("sortOrder");
            if (sortOrder == null || sortOrder.isEmpty()) {
                sortOrder = "asc"; // Default sorting order
            }

            int page = 1;
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1;
            }

            int start = (page - 1) * PAGE_SIZE;

            ProductWithDetailsDAO productDAO = new ProductWithDetailsDAO();
            try {
                List<ProductWithDetails> products = productDAO.getProductWithDetails(start, PAGE_SIZE, search, sortColumn, sortOrder);
                int totalProducts = productDAO.getProductCount(search);
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
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
//        processRequest(req, resp);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
//        processRequest(req, resp);
//    }
}
