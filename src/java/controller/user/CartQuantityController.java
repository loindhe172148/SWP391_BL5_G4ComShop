package controller.user;

import dal.CartDao;
import dal.UserDBContext;
import entity.Account;
import entity.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CartQuantityController", urlPatterns = {"/CartQuantityController"})
public class CartQuantityController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy thông tin tài khoản từ session
        Account account = (Account) request.getSession().getAttribute("account");

        // Nếu người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
        if (account == null) {
            response.sendRedirect("login");
            return;
        }

        // Lấy thông tin cần thiết từ request
       int userId = account.getId();
        int productDetailId = Integer.parseInt(request.getParameter("itemId")); // Được lấy từ request
        String action = request.getParameter("action");

        // Khởi tạo DAO để làm việc với cơ sở dữ liệu
        CartDao cartDao = new CartDao();

        // Xử lý tăng hoặc giảm số lượng sản phẩm dựa trên productDetailId
        if ("increase".equals(action)) {
            cartDao.increaseItemQuantity(userId, productDetailId);
        } else if ("decrease".equals(action)) {
            cartDao.decreaseItemQuantity(userId, productDetailId);
        }

        // Sau khi xử lý, chuyển hướng lại giỏ hàng để cập nhật hiển thị
        response.sendRedirect("CartController"); // Assuming "ViewCart" is the controller for displaying the cart
    }

    @Override
    public String getServletInfo() {
        return "Handles increasing and decreasing quantities in the cart";
    }
}
