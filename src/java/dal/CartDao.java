package dal;

import entity.CartDetail;
import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class CartDao extends DBContext<CartDetail> {

    // Method to retrieve cart items for a specific user
    public Map<Integer, Map<Product, Double>> getCartItemsByUserId(int userId) {
    Map<Integer, Map<Product, Double>> items = new HashMap<>();
    String query = "SELECT pd.id as productdetailid, p.*, pd.saleprice, cd.quantity as cart_quantity " +
                   "FROM CartDetail cd " +
                   "JOIN ProductDetail pd ON cd.productdetailid = pd.id " +
                   "JOIN Product p ON pd.productid = p.id " +
                   "WHERE cd.customerid = ?";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, userId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int productDetailId = rs.getInt("productdetailid");
                
                Product item = new Product();
                item.setId(rs.getInt("id")); // Product ID from the Product table
                item.setName(rs.getString("name"));
                item.setQuantity(rs.getInt("cart_quantity")); // Quantity from CartDetail

                double saleprice = rs.getDouble("saleprice"); // Sale price from ProductDetail

                // Store the product in a map with productdetailid as the key
                Map<Product, Double> productWithPrice = new HashMap<>();
                productWithPrice.put(item, saleprice);
                items.put(productDetailId, productWithPrice);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return items;
}


    // Method to increase the quantity of a product in the cart
    public void increaseItemQuantity(int userId, int productDetailId) {
        String query = "UPDATE CartDetail SET quantity = quantity + 1 " +
                       "WHERE customerid = ? AND productdetailid = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, productDetailId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to decrease the quantity of a product in the cart
    public void decreaseItemQuantity(int userId, int productDetailId) {
        String query = "UPDATE CartDetail SET quantity = CASE WHEN quantity > 1 THEN quantity - 1 ELSE quantity END " +
                       "WHERE customerid = ? AND productdetailid = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, productDetailId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to remove an item from the cart
    public void deleteFromCart(int userId, int productDetailId) {
        String query = "DELETE FROM CartDetail WHERE customerid = ? AND productdetailid = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, productDetailId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
