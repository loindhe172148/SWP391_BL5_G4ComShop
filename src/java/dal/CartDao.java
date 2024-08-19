package dal;

import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class CartDao extends DBContext_1 {

    // Method to retrieve cart items for a specific user
   public Map<Integer, Map<Product, Double>> getCartItemsByUserId(int userId) {
    Map<Integer, Map<Product, Double>> items = new HashMap<>();
    String query = "SELECT pd.id as productdetailid, p.id, p.name, p.image, pd.saleprice, cd.quantity as cart_quantity " +
                   "FROM CartDetail cd " +
                   "JOIN ProductDetail pd ON cd.productdetailid = pd.id " +
                   "JOIN Product p ON pd.productid = p.id " +
                   "WHERE cd.customerid = ?";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, userId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int productDetailId = rs.getInt("productdetailid");

                // Create a Product object and set its attributes
                Product item = new Product();
                item.setId(rs.getInt("id")); 
                item.setName(rs.getString("name"));
                item.setQuantity(rs.getInt("cart_quantity"));
                item.setImage(rs.getString("image")); // Set the image URL

                double saleprice = rs.getDouble("saleprice");

                // Create a map to hold the product and its price
                Map<Product, Double> productWithPrice = new HashMap<>();
                productWithPrice.put(item, saleprice);

                // Add the product detail ID and the product with price to the items map
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
