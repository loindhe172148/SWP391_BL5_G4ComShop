package controller.customer;

import dal.CartDetailDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

public class AddToCartController extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        CartDetailDAO cartDAO = new CartDetailDAO();

        try (PrintWriter out = response.getWriter()) {
            // Lấy thông tin người dùng từ session
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("loggedInUser");

            if (user != null) {
                int customerId = user.getId();

                int productDetailId = Integer.parseInt(request.getParameter("productDetailId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                float price = Float.parseFloat(request.getParameter("price"));
                float totalPrice = Float.parseFloat(request.getParameter("totalPrice"));

                // Thêm sản phẩm vào giỏ hàng
                cartDAO.addToCart(productDetailId, quantity, price, totalPrice, customerId);

                response.sendRedirect(request.getContextPath() + "/productdetail?detailId=" + productDetailId);
            } else {

                int productDetailId = Integer.parseInt(request.getParameter("productDetailId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                float price = Float.parseFloat(request.getParameter("price"));
                float totalPrice = Float.parseFloat(request.getParameter("totalPrice"));

                // Thêm sản phẩm vào giỏ hàng
                try {
                cartDAO.addToCart(productDetailId, quantity, price, totalPrice, 1);
                // chuyển hướng về controller /productdetail đi kèm với productdetailId
                response.sendRedirect(request.getContextPath() + "/productdetail?detailId=" + productDetailId);
                } catch (Exception e) {
                    // Hiển thị thông báo lỗi cho người dùng
                    request.setAttribute("errorMessage", "Sản phẩm đã có trong giỏ hàng. Vui lòng kiểm tra giỏ hàng của bạn.");
                    request.getRequestDispatcher("/cart.jsp").forward(request, response); // Điều hướng đến trang giỏ hàng hoặc trang thông báo lỗi
                }
            
                // Nếu người dùng chưa đăng nhập, trả về lỗi
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be logged in to add items to the cart.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format");
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
