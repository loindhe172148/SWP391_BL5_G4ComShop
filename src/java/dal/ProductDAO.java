package dal;

import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DBContext<Product> {

    
    public List<Product> getAllProduct() {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM Product";

    try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setTitle(rs.getString("title"));
            product.setDescription(rs.getString("description"));
            product.setImage(rs.getString("image"));
            product.setQuantity(rs.getInt("quantity"));
            product.setOriginPrice(rs.getFloat("originprice"));
            product.setSalePrice(rs.getFloat("saleprice"));
            product.setCategoryId(rs.getInt("categoryid"));
            product.setBrandId(rs.getInt("brandid"));
            product.setScreenSize(rs.getFloat("screensize"));
            product.setCreateDate(rs.getDate("createdate"));
            product.setUpdateDate(rs.getDate("updatedate"));
            product.setStatus(rs.getString("status"));
            products.add(product);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return products;
}
    public List<Product> getNewestProducts(int limit) {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT TOP (?) * FROM Product ORDER BY createdate DESC";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, limit);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setTitle(rs.getString("title"));
            product.setDescription(rs.getString("description"));
            product.setImage(rs.getString("image"));
            product.setQuantity(rs.getInt("quantity"));
            product.setOriginPrice(rs.getFloat("originprice"));
            product.setSalePrice(rs.getFloat("saleprice"));
            product.setCategoryId(rs.getInt("categoryid"));
            product.setBrandId(rs.getInt("brandid"));
            product.setScreenSize(rs.getFloat("screensize"));
            product.setCreateDate(rs.getDate("createdate"));
            product.setUpdateDate(rs.getDate("updatedate"));
            product.setStatus(rs.getString("status"));
            products.add(product);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return products;
}
    public List<Product> getTopDiscountedProducts(int limit) {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT TOP (?) * FROM Product ORDER BY (originprice - saleprice) / originprice DESC";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, limit);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setTitle(rs.getString("title"));
                product.setDescription(rs.getString("description"));
                product.setImage(rs.getString("image"));
                product.setQuantity(rs.getInt("quantity"));
                product.setOriginPrice(rs.getFloat("originprice"));
                product.setSalePrice(rs.getFloat("saleprice"));
                product.setCategoryId(rs.getInt("categoryid"));
                product.setBrandId(rs.getInt("brandid"));
                product.setScreenSize(rs.getFloat("screensize"));
                product.setCreateDate(rs.getDate("createdate"));
                product.setUpdateDate(rs.getDate("updatedate"));
                product.setStatus(rs.getString("status"));
                products.add(product);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return products;
}
    public List<Product> getFilterProducts(String idBrandName) {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM [dbo].[Product] WHERE brandid = 1";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, idBrandName);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setTitle(rs.getString("title"));
            product.setDescription(rs.getString("description"));
            product.setImage(rs.getString("image"));
            product.setQuantity(rs.getInt("quantity"));
            product.setOriginPrice(rs.getFloat("originprice"));
            product.setSalePrice(rs.getFloat("saleprice"));
            product.setCategoryId(rs.getInt("categoryid"));
            product.setBrandId(rs.getInt("brandid"));
            product.setScreenSize(rs.getFloat("screensize"));
            product.setCreateDate(rs.getDate("createdate"));
            product.setUpdateDate(rs.getDate("updatedate"));
            product.setStatus(rs.getString("status"));
            products.add(product);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return products;
}
    
    
    
     



}
