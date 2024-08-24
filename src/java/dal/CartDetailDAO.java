package dal;

import entity.CartDetail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartDetailDAO extends DBContext<CartDetail> {

    // Thêm mục vào giỏ hàng
    public boolean addToCart(int productDetailId, int quantity, float price, int customerId) {
        if (checkAvailbleQuantity(productDetailId, customerId, quantity)) {
            updateQuantityItemCart(productDetailId, customerId, quantity);
        } else {
            String sql = "INSERT INTO CartDetail (productDetailId, quantity, price, customerId) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, productDetailId);
                ps.setInt(2, quantity);
                ps.setFloat(3, price);
                ps.setInt(4, customerId);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(CartDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }            
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
                    cart.setCustomerId(rs.getInt("customerId"));
                    carts.add(cart);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carts;
    }

    private void updateQuantityItemCart(int productId, int customerid, int val) {
        String sql = "update CartDetail\n"
                + "set quantity = quantity + ?\n"
                + "where productdetailid = ? and customerid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, val);
            ps.setInt(2, productId);
            ps.setInt(3, customerid);
            int x = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CartDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int checkExistItemCart(int productId, int customerid) {
        String sql = "select quantity from [CartDetail] where productdetailid = ? and customerid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, customerid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    private boolean checkAvailbleQuantity(int productId, int customerid, int val){
        int quantity = checkExistItemCart(productId, customerid);        
        ProductWithDetailsDAO db = new ProductWithDetailsDAO();
        if(quantity != 0){
            int available = db.getQuantity(productId);
            int result = quantity + val;
            return result <= available;
        }
        return false;
    }
    
}
