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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xuant
 */
public class ProductDAO extends DBContext {

    public List<Product> getAllProduct(String query) {
        if(query == null || query.equals("")){
            query = "SELECT [id], [name] FROM [G4COMShop].[dbo].[Product]";
        }
        List<Product> products = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id")) ;
                p.setName(rs.getString("name"));
//                String description;
//                String status;
//                double originPrice;
//                double salePrice;
//                String image;
//                int categoryId;
//                double capacity;
//                double size;
//                String color;
//                int cpuId;
//                int cardId;
//                int ramId;
//                int typeId;
//                int quantity;
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return products;
    }

}
