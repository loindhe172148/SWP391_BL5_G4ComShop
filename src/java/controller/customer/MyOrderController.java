package controller.customer;

import dal.OrderDAO;
import entity.Account;
import entity.Order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class MyOrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        int customerId = (account != null && !account.equals("")) ? account.getId() : 1;

        // Lấy tham số từ yêu cầu
        String sortBy = request.getParameter("sortBy");
        String order = request.getParameter("order");
        int pageNumber = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
        int pageSize = 5; // Số lượng đơn hàng trên mỗi trang

        // Tham số để xác định loại hành động
        String action = request.getParameter("action");
        String orderIdParam = request.getParameter("orderId");

        // Khởi tạo DAO với kết nối
        OrderDAO orderDAO = new OrderDAO();

        if (action != null && orderIdParam != null) {
            int orderId = Integer.parseInt(orderIdParam);

            switch (action) {
                case "cancel":
                    orderDAO.updateOrderStatus(orderId, "canceled");
                    break;
                case "feedback":
                    orderDAO.updateOrderStatus(orderId, "feedback_received");
                    break;
                case "confirmdelivery":
                    orderDAO.updateOrderStatus(orderId, "delivered");
                    break;
                case "reorder":
                    boolean reorderSuccess = orderDAO.reorder(orderId, customerId);
                    if (reorderSuccess) {
                        request.setAttribute("message", "Đặt hàng lại thành công!");
                    } else {
                        request.setAttribute("message", "Đặt hàng lại thất bại. Vui lòng thử lại.");
                    }
                    break;
                default:
                    break;
            }
        }

        // Lấy danh sách đơn hàng với phân trang và sắp xếp
        List<Order> orders = orderDAO.getOrders(customerId, sortBy, order, pageNumber, pageSize);
        int totalOrders = orderDAO.getTotalOrders(customerId);
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

        // Gửi thông tin đến trang JSP
        request.setAttribute("orders", orders);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("order", order);

        request.getRequestDispatcher("/view/customer/MyOrder.jsp").forward(request, response);
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
