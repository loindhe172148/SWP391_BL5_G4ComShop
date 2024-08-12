/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xuant
 */
public class ProductDAO extends ConnectDB {

    public List<Product> getAllProduct(String query, int offset, int limit) {
    if (query == null || query.equals("")) {
        query = "SELECT * FROM [G4COMShop].[dbo].[Product] ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    } else {
        query += " ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    }
    List<Product> products = new ArrayList<>();
    try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, offset);
        ps.setInt(2, limit);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setStatus(rs.getInt("status"));
                p.setOriginPrice(rs.getDouble("originprice"));
                p.setSalePrice(rs.getDouble("saleprice"));
                p.setImage(rs.getString("image"));
                p.setCategoryId(rs.getInt("categoryid"));
                p.setCapacity(rs.getDouble("capacity"));
                p.setSize(rs.getDouble("size"));
                p.setColor(rs.getString("color"));
                p.setCpuId(rs.getInt("CPUid"));
                p.setCardId(rs.getInt("cardid"));
                p.setRamId(rs.getInt("RAMid"));
                p.setTypeId(rs.getInt("typeid"));
                p.setQuantity(rs.getInt("quantity"));
                products.add(p);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return products;
}


    public Product getProductById(int id) {
        String query = "SELECT * FROM [G4COMShop].[dbo].[Product] WHERE [id] = ?";
        Product p = new Product();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setDescription(rs.getString("description"));
                    p.setStatus(rs.getInt("status"));
                    p.setOriginPrice(rs.getDouble("originprice"));
                    p.setSalePrice(rs.getDouble("saleprice"));
                    p.setImage(rs.getString("image"));
                    p.setCategoryId(rs.getInt("categoryid"));
                    p.setCapacity(rs.getDouble("capacity"));
                    p.setSize(rs.getDouble("size"));
                    p.setColor(rs.getString("color"));
                    p.setCpuId(rs.getInt("CPUid"));
                    p.setCardId(rs.getInt("cardid"));
                    p.setRamId(rs.getInt("RAMid"));
                    p.setTypeId(rs.getInt("typeid"));
                    p.setQuantity(rs.getInt("quantity"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public void addProduct(Product p) {
        String query = "INSERT INTO [G4COMShop].[dbo].[Product] "
                + "([name], [description], [status], [originprice], [saleprice], [image], "
                + "[categoryid], [capacity], [size], [color], [cpuId], [cardId], [ramId], [typeId], [quantity]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setInt(3, p.getStatus());
            ps.setDouble(4, p.getOriginPrice());
            ps.setDouble(5, p.getSalePrice());
            ps.setString(6, p.getImage());  // Assuming you are storing the image filename
            ps.setInt(7, p.getCategoryId());
            ps.setDouble(8, p.getCapacity());
            ps.setDouble(9, p.getSize());
            ps.setString(10, p.getColor());
            ps.setInt(11, p.getCpuId());
            ps.setInt(12, p.getCardId());
            ps.setInt(13, p.getRamId());
            ps.setInt(14, p.getTypeId());
            ps.setInt(15, p.getQuantity());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateProduct(Product p) {
        String query = "UPDATE [G4COMShop].[dbo].[Product] "
                + "SET [name] = ?, [description] = ?, [status] = ?, [originprice] = ?, [saleprice] = ?, "
                + "[image] = ?, [categoryid] = ?, [capacity] = ?, [size] = ?, [color] = ?, "
                + "[cpuId] = ?, [cardId] = ?, [ramId] = ?, [typeId] = ?, [quantity] = ? "
                + "WHERE [id] = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setInt(3, p.getStatus());
            ps.setDouble(4, p.getOriginPrice());
            ps.setDouble(5, p.getSalePrice());
            ps.setString(6, p.getImage());  // Assuming you are storing the image filename
            ps.setInt(7, p.getCategoryId());
            ps.setDouble(8, p.getCapacity());
            ps.setDouble(9, p.getSize());
            ps.setString(10, p.getColor());
            ps.setInt(11, p.getCpuId());
            ps.setInt(12, p.getCardId());
            ps.setInt(13, p.getRamId());
            ps.setInt(14, p.getTypeId());
            ps.setInt(15, p.getQuantity());
            ps.setInt(16, p.getId());  // Set the ID for the WHERE clause

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean deleteProductById(int productId) {
        String query = "DELETE FROM [G4COMShop].[dbo].[Product] WHERE [id] = ?";
        boolean isDeleted = false;

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productId); // Set the ID parameter in the query

            int rowsAffected = ps.executeUpdate(); // Execute the update

            if (rowsAffected > 0) {
                isDeleted = true; // Product was deleted successfully
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isDeleted;
    }
    public void updateProductStatus(int productId, int status) {
    String query = "UPDATE [G4COMShop].[dbo].[Product] SET status = ? WHERE id = ?";
    try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, status);
        ps.setInt(2, productId);
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    public int getTotalProductCount() {
    int count = 0;
    String query = "SELECT COUNT(*) FROM [G4COMShop].[dbo].[Product]";
    try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return count;
}


}
