package dal;

import entity.Product;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DBContext<Product> {

    // Tam
    public int getProductCount(String status, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT COUNT(*) FROM Product WHERE 1=1";

        if (status != null && !status.isEmpty()) {
            sql += " AND status = ?";
        }

        if (startDate != null && endDate != null) {
            sql += " AND createDate BETWEEN ? AND ?";
        } else {
            // Default to the last 7 days
            endDate = LocalDate.now();
            startDate = endDate.minusDays(7);
            sql += " AND createDate BETWEEN ? AND ?";
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            int index = 1;

            if (status != null && !status.isEmpty()) {
                stmt.setString(index++, status);
            }

            stmt.setDate(index++, Date.valueOf(startDate));
            stmt.setDate(index, Date.valueOf(endDate));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getProductCount() {
        return getProductCount(null, null, null);
    }

    public int getShowingProductCount(LocalDate startDate, LocalDate endDate) {
        return getProductCount("Showing", startDate, endDate);
    }

    public int getHidingProductCount(LocalDate startDate, LocalDate endDate) {
        return getProductCount("Hiding", startDate, endDate);
    }
    // Linh
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
