package controller.marketing;

import controller.user.BaseRequiredAuthenticationController;
import dal.AccountWithUserDAO;
import dal.OrderDAO;
import dal.ProductDAO;
import dal.ProductWithDetailsDAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class Dashboard extends BaseRequiredAuthenticationController {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        ProductDAO productDAO = new ProductDAO();
        ProductWithDetailsDAO productDetailsDAO = new ProductWithDetailsDAO();
        AccountWithUserDAO accountWithUserDAO = new AccountWithUserDAO();
        OrderDAO orderDAO = new OrderDAO();

        // Retrieve filter parameters from the request
        String startDateParam = request.getParameter("startDate");
        String endDateParam = request.getParameter("endDate");

        // Parse the date parameters, use defaults if null
        LocalDate startDate = startDateParam != null && !startDateParam.isEmpty() ? LocalDate.parse(startDateParam) : LocalDate.now().minusDays(7);
        LocalDate endDate = endDateParam != null && !endDateParam.isEmpty() ? LocalDate.parse(endDateParam) : LocalDate.now();

        // Get the counts for products
        int totalProducts = productDAO.getProductCount(null, startDate, endDate);
        int showingProducts = productDAO.getProductCount("Showing", startDate, endDate);
        int hidingProducts = productDAO.getProductCount("Hiding", startDate, endDate);

        // Get the counts for accounts
        int totalAccounts = accountWithUserDAO.getAccountWithUserCount(null, startDate, endDate);
        int admins = accountWithUserDAO.getAccountWithUserCount("Admin", startDate, endDate);
        int marketings = accountWithUserDAO.getAccountWithUserCount("Marketing", startDate, endDate);
        int sales = accountWithUserDAO.getAccountWithUserCount("Sale", startDate, endDate);
        int customers = accountWithUserDAO.getAccountWithUserCount("Customer", startDate, endDate);

        // Get the counts for orders
        int totalOrders = orderDAO.getOrderCount(null, startDate, endDate);
        int processingOrders = orderDAO.getOrderCount("processing", startDate, endDate);
        int deliveredOrders = orderDAO.getOrderCount("delivered", startDate, endDate);
        int deliveringOrders = orderDAO.getOrderCount("delivering", startDate, endDate);
        int canceledOrders = orderDAO.getOrderCount("canceled", startDate, endDate);
        int rejectedOrders = orderDAO.getOrderCount("rejected", startDate, endDate);

        // Set attributes to pass to JSP
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("showingProducts", showingProducts);
        request.setAttribute("hidingProducts", hidingProducts);
        
        request.setAttribute("totalAccounts", totalAccounts);
        request.setAttribute("admins", admins);
        request.setAttribute("marketings", marketings);
        request.setAttribute("sales", sales);
        request.setAttribute("customers", customers);
        
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("processingOrders", processingOrders);
        request.setAttribute("deliveredOrders", deliveredOrders);
        request.setAttribute("deliveringOrders", deliveringOrders);
        request.setAttribute("canceledOrders", canceledOrders);
        request.setAttribute("rejectedOrders", rejectedOrders);

        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        // Forward to the dashboard view
        request.getRequestDispatcher("/view/marketing/dashboard.jsp").forward(request, response);
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

