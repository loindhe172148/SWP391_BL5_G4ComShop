package dal;
import entity.CartDetail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartDetailDAO extends DBContext<CartDetail> {

    // Thêm mục vào giỏ hàng
    public boolean addToCart(int productDetailId, int quantity, float price, float totalPrice, int customerId) {
        String sql = "INSERT INTO CartDetail (productDetailId, quantity, price, totalPrice, customerId) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productDetailId);
            ps.setInt(2, quantity);
            ps.setFloat(3, price);
            ps.setFloat(4, totalPrice);
            ps.setInt(5, customerId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    // Lấy tất cả các mục trong giỏ hàng của một khách hàng
    public List<CartDetail> getCartByCustomerId(int customerId) {
        List<CartDetail> carts = new ArrayList<>();
        String sql = "SELECT * FROM Cart WHERE customerId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CartDetail cart = new CartDetail();
                    cart.setProductDetailId(rs.getInt("productDetailId"));
                    cart.setQuantity(rs.getInt("quantity"));
                    cart.setPrice(rs.getFloat("price"));
                    cart.setTotalPrice(rs.getFloat("totalPrice"));
                    cart.setCustomerId(rs.getInt("customerId"));
                    carts.add(cart);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carts;
    }
}

