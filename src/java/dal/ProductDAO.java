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
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Product (name, title, description, image, categoryId, brandId, screenSize, createDate, updateDate, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getTitle());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getImage());
            stmt.setInt(5, product.getCategoryId());
            stmt.setInt(6, product.getBrandId());
            stmt.setFloat(7, product.getScreenSize());
            stmt.setDate(8, new Date(product.getCreateDate().getTime()));
            stmt.setDate(9, new Date(product.getUpdateDate().getTime()));
            stmt.setString(10, product.getStatus());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
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
    public void addProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Linh
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, pd.originprice, pd.saleprice,pd.id \n"
                + "                 FROM Product p \n"
                + "                 JOIN ProductDetail pd ON p.id = pd.productId";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setTitle(rs.getString("title"));
                product.setDescription(rs.getString("description"));
                product.setImage(rs.getString("image"));
                product.setCategoryId(rs.getInt("categoryid"));
                product.setBrandId(rs.getInt("brandid"));
                product.setScreenSize(rs.getFloat("screensize"));
                product.setCreateDate(rs.getDate("createdate"));
                product.setUpdateDate(rs.getDate("updatedate"));
                product.setStatus(rs.getString("status"));
                product.setOriginPrice(rs.getFloat("originprice"));
                product.setSalePrice(rs.getFloat("saleprice"));
                product.setproductdetailID(rs.getInt(14));
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public List<Product> getNewestProducts(int limit) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT TOP (?) p.*, pd.originprice, pd.saleprice ,pd.id\n"
                + "                 FROM Product p\n"
                + "                 JOIN ProductDetail pd ON p.id = pd.productId \n"
                + "                 ORDER BY p.createdate DESC";

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
                product.setCategoryId(rs.getInt("categoryid"));
                product.setBrandId(rs.getInt("brandid"));
                product.setScreenSize(rs.getFloat("screensize"));
                product.setCreateDate(rs.getDate("createdate"));
                product.setUpdateDate(rs.getDate("updatedate"));
                product.setStatus(rs.getString("status"));
                product.setOriginPrice(rs.getFloat("originprice"));
                product.setSalePrice(rs.getFloat("saleprice"));
                product.setproductdetailID(rs.getInt(14));

                // You can add these to your Product class if needed
                // product.setOriginPrice(originPrice);
                // product.setSalePrice(salePrice);
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public List<Product> getTopDiscountedProducts(int limit) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT TOP (?) p.*, pd.originprice, pd.saleprice,pd.id "
                + "FROM Product p "
                + "JOIN ProductDetail pd ON p.id = pd.productId "
                + "ORDER BY (pd.originprice - pd.saleprice) / pd.originprice DESC";

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
                product.setCategoryId(rs.getInt("categoryid"));
                product.setBrandId(rs.getInt("brandid"));
                product.setScreenSize(rs.getFloat("screensize"));
                product.setCreateDate(rs.getDate("createdate"));
                product.setUpdateDate(rs.getDate("updatedate"));
                product.setStatus(rs.getString("status"));
                product.setOriginPrice(rs.getFloat("originprice"));
                product.setSalePrice(rs.getFloat("saleprice"));
                product.setproductdetailID(rs.getInt(14));
                products.add(product);
                // You can add these to your Product class if needed
                // product.setOriginPrice(originPrice);
                // product.setSalePrice(salePrice);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public List<Product> getFilterProducts(String idBrandName) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, pd.originprice, pd.saleprice,pd.id "
                + "FROM Product p "
                + "JOIN ProductDetail pd ON p.id = pd.productId "
                + "WHERE p.brandId = (?)"; // Đảm bảo rằng tên cột trong cơ sở dữ liệu và tên trong SQL khớp

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, idBrandName); // Nếu idBrandName là chuỗi, sử dụng setString
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setTitle(rs.getString("title"));
                product.setDescription(rs.getString("description"));
                product.setImage(rs.getString("image"));
                product.setCategoryId(rs.getInt("categoryid"));
                product.setBrandId(rs.getInt("brandid")); // Đảm bảo tên cột khớp
                product.setScreenSize(rs.getFloat("screensize"));
                product.setCreateDate(rs.getDate("createdate"));
                product.setUpdateDate(rs.getDate("updatedate"));
                product.setStatus(rs.getString("status"));
                product.setOriginPrice(rs.getFloat("originprice"));
                product.setSalePrice(rs.getFloat("saleprice"));
                product.setproductdetailID(rs.getInt(14));
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

}
