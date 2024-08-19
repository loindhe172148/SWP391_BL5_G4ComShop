package dal;

import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DBContext<ProductJoinDetail> {

    public List<ProductJoinDetail> getProducts(int start, int total, String search) {
        List<ProductJoinDetail> products = new ArrayList<>();
        String sql = "SELECT p.*, pd.ramId, pd.cpuId, pd.cardId, pd.variantPrice " +
                     "FROM Product p " +
                     "JOIN ProductDetail pd ON p.id = pd.productid " +
                     "WHERE p.name LIKE ? " +
                     "ORDER BY p.id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + search + "%");
            stmt.setInt(2, start);
            stmt.setInt(3, total);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProductJoinDetail product = new ProductJoinDetail();
                product.setId(rs.getInt("p.id"));
                product.setName(rs.getString("p.name"));
                product.setTitle(rs.getString("p.title"));
                product.setDescription(rs.getString("p.description"));
                product.setImage(rs.getString("p.image"));
                product.setQuantity(rs.getInt("p.quantity"));
                product.setOriginPrice(rs.getFloat("p.originprice"));
                product.setSalePrice(rs.getFloat("p.saleprice"));
                product.setCategoryId(rs.getInt("p.categoryid"));
                product.setBrandId(rs.getInt("p.brandid"));
                product.setScreenSize(rs.getFloat("p.screensize"));
                product.setCreateDate(rs.getDate("p.createdate"));
                product.setUpdateDate(rs.getDate("p.updatedate"));
                product.setStatus(rs.getString("p.status"));
                
                product.setRamId(rs.getInt("pd.ramId"));
                product.setCpuId(rs.getInt("pd.cpuId"));
                product.setCardId(rs.getInt("pd.cardId"));
                product.setVariantPrice(rs.getDouble("pd.variantPrice"));
                
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public int getProductCount(String search) {
        String sql = "SELECT COUNT(*) FROM Product WHERE name LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + search + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
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
