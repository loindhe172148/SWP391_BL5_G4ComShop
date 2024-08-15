/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketing;

import controller.user.BaseRequiredAuthenticationController;
import dal.ProductDAO;
import dal.ProductJoinDetail;
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

/**
 *
 * @author xuant
 */
@WebServlet(name = "ProductListController", urlPatterns = {"/marketing/productlist", "/marketing/changestatus"})
public class ProductListController extends HttpServlet {

    private static final int PAGE_SIZE = 10; // Number of products per page

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }

            int page = 1;
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1;
            }

            int start = (page - 1) * PAGE_SIZE;

            ProductDAO productDAO = new ProductDAO();
            try {
                List<ProductJoinDetail> products = productDAO.getProducts(start, PAGE_SIZE, search);
                int totalProducts = productDAO.getProductCount(search);
                int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);

                request.setAttribute("products", products);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("currentPage", page);
                request.setAttribute("search", search); // Add this line to pass search term
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
