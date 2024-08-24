package dal;

import entity.Product;
import java.sql.*;
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

    public int addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Product (name, title, description, image, categoryId, brandId, screenSize, createDate, updateDate, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int productId = -1; // Default value if insertion fails

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        productId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return productId;
    }

    public void updateProductStatus(int productId, String newStatus) throws SQLException {
        String sql = "UPDATE Product SET status = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, productId);
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

    public List<Product> getProductsByPage(int page, int pageSize) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, pd.originprice, pd.saleprice, pd.id "
                + "FROM Product p "
                + "JOIN ProductDetail pd ON p.id = pd.productId "
                + "ORDER BY p.id "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, (page - 1) * pageSize); // Offset calculation
            ps.setInt(2, pageSize); // Limit calculation
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
                product.setproductdetailID(rs.getInt("id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public int getTotalProducts() {
        String sql = "SELECT COUNT(*) FROM Product";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
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

    public List<Product> getFilterProducts(String idBrandName, int page, int pageSize) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, pd.originprice, pd.saleprice, pd.id "
                + "FROM Product p "
                + "JOIN ProductDetail pd ON p.id = pd.productId "
                + "WHERE p.brandId = ? "
                + "ORDER BY p.id "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        int offset = (page - 1) * pageSize;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, idBrandName);
            ps.setInt(2, offset);
            ps.setInt(3, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setTitle(rs.getString("title"));
                product.setDescription(rs.getString("description"));
                product.setImage(rs.getString("image"));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setBrandId(rs.getInt("brandId"));
                product.setScreenSize(rs.getFloat("screenSize"));
                product.setCreateDate(rs.getDate("createDate"));
                product.setUpdateDate(rs.getDate("updateDate"));
                product.setStatus(rs.getString("status"));
                product.setOriginPrice(rs.getFloat("originPrice"));
                product.setSalePrice(rs.getFloat("salePrice"));
                product.setproductdetailID(rs.getInt("id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public int getTotalFilteredProducts(String idBrandName) {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM Product WHERE brandId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, idBrandName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return total;
    }

    public List<Product> getSearchProducts(String query, int page, int pageSize) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, pd.originprice, pd.saleprice, pd.id "
                + "FROM Product p "
                + "JOIN ProductDetail pd ON p.id = pd.productId "
                + "WHERE p.name LIKE ? "
                + "ORDER BY p.id "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        int offset = (page - 1) * pageSize;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + query + "%");
            ps.setInt(2, offset);
            ps.setInt(3, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setTitle(rs.getString("title"));
                product.setDescription(rs.getString("description"));
                product.setImage(rs.getString("image"));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setBrandId(rs.getInt("brandId"));
                product.setScreenSize(rs.getFloat("screenSize"));
                product.setCreateDate(rs.getDate("createDate"));
                product.setUpdateDate(rs.getDate("updateDate"));
                product.setStatus(rs.getString("status"));
                product.setOriginPrice(rs.getFloat("originPrice"));
                product.setSalePrice(rs.getFloat("salePrice"));
                product.setproductdetailID(rs.getInt("id"));
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public int getTotalSearchProducts(String query) {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM Product WHERE name LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + query + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return total;
    }

}

