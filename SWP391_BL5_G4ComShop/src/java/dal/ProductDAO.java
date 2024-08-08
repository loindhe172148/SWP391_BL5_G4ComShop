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

    public List<Product> getAllProduct(String query) {
        if (query == null || query.equals("")) {
            query = "SELECT [id], [name], [description], [status], [originprice],"
                    + " [saleprice], [image], [categoryid], [capacity], [size], [color], [quantity] "
                    + "FROM [G4COMShop].[dbo].[Product]";
        }
        List<Product> products = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setStatus(rs.getInt("status"));
                p.setOriginPrice(rs.getDouble("originprice"));
                p.setSalePrice(rs.getDouble("saleprice"));
                p.setImage(rs.getString("image"));
                p.setImage(rs.getString("image"));
                p.setCategoryId(rs.getInt("categoryid"));
                p.setCapacity(rs.getDouble("capacity"));
                p.setSize(rs.getDouble("size"));
                p.setColor(rs.getString("color"));
                p.setQuantity(rs.getInt("quantity"));

                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

}
