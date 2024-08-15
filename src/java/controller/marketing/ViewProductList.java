///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller.marketing;
//
//import controller.user.BaseRequiredAuthenticationController;
//import dal.CPUDAO;
//import dal.CardDAO;
//import dal.CategoryDAO;
//import dal.ProductDAO;
//import dal.RAMDAO;
//import dal.TypeDAO;
//import entity.Account;
//import entity.CPU;
//import entity.Card;
//import entity.Category;
//import entity.Product;
//import entity.RAM;
//import entity.Type;
//import java.io.IOException;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.util.List;
//import java.sql.SQLException;
//
///**
// *
// * @author xuant
// */
//@WebServlet(name = "ViewProductList", urlPatterns = {"/marketing/changestatus"})
////public class ViewProductList extends BaseRequiredAuthenticationController {
//public class ViewProductList extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        String action = request.getServletPath();
//        int pageSize = 5;
//        int currentPage = 1;
//        if (request.getParameter("page") != null) {
//            try {
//                currentPage = Integer.parseInt(request.getParameter("page"));
//            } catch (NumberFormatException e) {
//                currentPage = 1;
//            }
//        }
//        int offset = (currentPage - 1) * pageSize;
//
//        try (PrintWriter out = response.getWriter()) {
//            ProductDAO productDAO = new ProductDAO();
//            CPUDAO cpuDAO = new CPUDAO();
//            CardDAO cardDAO = new CardDAO();
//            CategoryDAO categoryDAO = new CategoryDAO();
//            TypeDAO typeDAO = new TypeDAO();
//            RAMDAO ramDAO = new RAMDAO();
//
//            List<CPU> cpuList = cpuDAO.getAllCPU();
//            request.setAttribute("cpuList", cpuList);
//
//            List<Card> cardList = cardDAO.getAllCard();
//            request.setAttribute("cardList", cardList);
//
//            List<Category> categoryList = categoryDAO.getAllCategory();
//            request.setAttribute("categoryList", categoryList);
//
//            List<Type> typeList = typeDAO.getAllType();
//            request.setAttribute("typeList", typeList);
//
//            List<RAM> ramList = ramDAO.getAllRAM();
//            request.setAttribute("ramList", ramList);
//
//            switch (action) {
//                case "/view/marketing/productlist":
//                    List<Product> products = productDAO.getAllProduct(null, offset, pageSize);
//                    request.setAttribute("products", products);
//
//                    int totalProducts = productDAO.getTotalProductCount(); // Method to get the total product count
//                    int totalPages = (int) Math.ceil(totalProducts / (double) pageSize);
//                    request.setAttribute("currentPage", currentPage);
//                    request.setAttribute("totalPages", totalPages);
//
//                    request.getRequestDispatcher("product-list.jsp").forward(request, response);
//                    break;
//                case "/view/marketing/changestatus":
//                    String idParam = request.getParameter("id");
//                    String statusParam = request.getParameter("status");
//
//                    if (idParam != null && statusParam != null) {
//                        int productId = Integer.parseInt(idParam);
//                        int status = Integer.parseInt(statusParam);
//                        productDAO.updateProductStatus(productId, status);
//
//                        response.sendRedirect(request.getContextPath() + "/view/marketing/productlist");
//                    }
//                    break;
//                case "/marketing/productlist":
//                    request.getRequestDispatcher("/view/marketing/data-table.jsp").forward(request, response);
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
////    @Override
////    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
////    }
////
////    @Override
////    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
////    }
//}
