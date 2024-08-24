package dal;

import entity.Product;
import entity.ProductDetail;
import entity.ProductWithDetails;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductWithDetailsDAO extends DBContext<ProductWithDetails> {

    public List<ProductWithDetails> getProductWithDetails(int start, int pageSize, String search, String sortColumn, String sortOrder) {
        List<ProductWithDetails> products = new ArrayList<>();

        if (sortColumn == null || sortColumn.isEmpty()) {
            sortColumn = "pd.id"; // Default sorting column
        } else {
            switch (sortColumn) {
                case "id":
                case "originPrice":
                case "salePrice":
                case "quantity":
                    sortColumn = "pd." + sortColumn;
                    break;
                default:
                    sortColumn = "p." + sortColumn;
                    break;
            }
        }

        // Construct SQL query with dynamic search conditions
        String query = "SELECT p.*, pd.* "
                + "FROM Product p "
                + "JOIN ProductDetail pd ON p.id = pd.productid "
                + "WHERE (p.name LIKE ? OR p.title LIKE ? OR p.description LIKE ?) "
                + "ORDER BY " + sortColumn + " " + sortOrder + " "
                + "OFFSET ? ROWS "
                + "FETCH NEXT ? ROWS ONLY;";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            String searchParam = "%" + search + "%";
            stmt.setString(1, searchParam);
            stmt.setString(2, searchParam);
            stmt.setString(3, searchParam);
            stmt.setInt(4, start);
            stmt.setInt(5, pageSize);

            ResultSet rs = stmt.executeQuery();

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

                ProductDetail productDetails = new ProductDetail();
                productDetails.setId(rs.getInt(12));
                productDetails.setProductId(rs.getInt("productId"));
                productDetails.setRamId(rs.getInt("ramId"));
                productDetails.setCpuId(rs.getInt("cpuId"));
                productDetails.setCardId(rs.getInt("cardId"));
                productDetails.setColor(rs.getString("color"));
                productDetails.setOriginPrice(rs.getDouble("originPrice"));
                productDetails.setSalePrice(rs.getDouble("salePrice"));
                productDetails.setQuantity(rs.getInt("quantity"));

                products.add(new ProductWithDetails(product, productDetails));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    public boolean updateProductWithDetails(ProductWithDetails productWithDetails) {
    boolean isUpdated = false;
    String updateProductQuery = "UPDATE Product SET name = ?, title = ?, description = ?, image = ?, categoryId = ?, brandId = ?, screenSize = ?, updateDate = ?, status = ? WHERE id = ?";
    String updateProductDetailQuery = "UPDATE ProductDetail SET ramId = ?, cpuId = ?, cardId = ?, color = ?, originPrice = ?, salePrice = ? WHERE productid = ?";

    try (PreparedStatement productStmt = connection.prepareStatement(updateProductQuery);
         PreparedStatement productDetailStmt = connection.prepareStatement(updateProductDetailQuery)) {

        // Update Product
        productStmt.setString(1, productWithDetails.getProduct().getName());
        productStmt.setString(2, productWithDetails.getProduct().getTitle());
        productStmt.setString(3, productWithDetails.getProduct().getDescription());
        productStmt.setString(4, productWithDetails.getProduct().getImage());
        productStmt.setInt(5, productWithDetails.getProduct().getCategoryId());
        productStmt.setInt(6, productWithDetails.getProduct().getBrandId());
        productStmt.setFloat(7, productWithDetails.getProduct().getScreenSize());
        productStmt.setDate(8, new java.sql.Date(productWithDetails.getProduct().getUpdateDate().getTime()));
        productStmt.setString(9, productWithDetails.getProduct().getStatus());
        productStmt.setInt(10, productWithDetails.getProduct().getId());
        
        // Update ProductDetail
        productDetailStmt.setInt(1, productWithDetails.getProductDetails().getRamId());
        productDetailStmt.setInt(2, productWithDetails.getProductDetails().getCpuId());
        productDetailStmt.setInt(3, productWithDetails.getProductDetails().getCardId());
        productDetailStmt.setString(4, productWithDetails.getProductDetails().getColor());
        productDetailStmt.setDouble(5, productWithDetails.getProductDetails().getOriginPrice());
        productDetailStmt.setDouble(6, productWithDetails.getProductDetails().getSalePrice());
        productDetailStmt.setInt(7, productWithDetails.getProduct().getId());

        // Execute update
        int productRows = productStmt.executeUpdate();
        int productDetailRows = productDetailStmt.executeUpdate();
        if(productRows <=0 ){
            System.out.println("loi o product");
        }
        isUpdated = (productRows > 0) && (productDetailRows > 0);
        if(productDetailRows <=0 ){
            System.out.println("loi o productdetail");
        }
        System.out.println(isUpdated);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return isUpdated;
}


    public ProductWithDetails getProductDetailById(int detailId) {
        ProductWithDetails productWithDetails = null;
        String query = "SELECT p.*, pd.* "
                + "FROM Product p "
                + "JOIN ProductDetail pd ON p.id = pd.productid "
                + "WHERE pd.id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, detailId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
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

                ProductDetail productDetails = new ProductDetail();
                productDetails.setId(rs.getInt(12));
                productDetails.setProductId(rs.getInt("productId"));
                productDetails.setRamId(rs.getInt("ramId"));
                productDetails.setCpuId(rs.getInt("cpuId"));
                productDetails.setCardId(rs.getInt("cardId"));
                productDetails.setColor(rs.getString("color"));
                productDetails.setOriginPrice(rs.getDouble("originPrice"));
                productDetails.setSalePrice(rs.getDouble("salePrice"));
                productDetails.setQuantity(rs.getInt("quantity"));

                productWithDetails = new ProductWithDetails(product, productDetails);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productWithDetails;
    }

    public List<ProductWithDetails> getProductsByBrand(int brandid, int limit) {
        List<ProductWithDetails> products = new ArrayList<>();
        String sql = "SELECT TOP (?) p.*, pd.* "
                + "FROM Product p "
                + "JOIN ProductDetail pd ON p.id = pd.productid "
                + "WHERE p.brandid = ? "
                + "ORDER BY NEWID();";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, limit);
            ps.setInt(2, brandid);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    ProductDetail productDetails = new ProductDetail();

                    // Cài đặt các trường của Product
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

                    // Cài đặt các trường của ProductDetail
                    productDetails.setId(rs.getInt(12));
                    productDetails.setProductId(rs.getInt("productId"));
                    productDetails.setRamId(rs.getInt("ramId"));
                    productDetails.setCpuId(rs.getInt("cpuId"));
                    productDetails.setCardId(rs.getInt("cardId"));
                    productDetails.setColor(rs.getString("color"));
                    productDetails.setOriginPrice(rs.getDouble("originPrice"));
                    productDetails.setSalePrice(rs.getDouble("salePrice"));
                    productDetails.setQuantity(rs.getInt("quantity"));

                    products.add(new ProductWithDetails(product, productDetails));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public int getProductCount(String search) {
        String sql = "SELECT COUNT(*) "
                + "FROM Product p "
                + "JOIN ProductDetail pd ON p.id = pd.productId "
                + "WHERE p.name LIKE ?";

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

    public int getProductWithDetailsCount(String status, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT COUNT(*) FROM Product p "
                + "JOIN ProductDetail pd ON p.id = pd.productId "
                + "WHERE p.createDate BETWEEN ? AND ?";

        // Add status filtering if status is provided
        if (status != null) {
            sql += " AND p.status = ?";
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            if (status != null) {
                stmt.setString(3, status);
            }
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<ProductWithDetails> getListProduct() {
        List<ProductWithDetails> productWithDetailsList = new ArrayList<>();
        String sql = "SELECT p.*, pd.* FROM Product p JOIN ProductDetail pd ON p.id = pd.productId";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Populate Product object
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setTitle(rs.getString("title"));
                product.setDescription(rs.getString("description"));
                product.setImage(rs.getString("image"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setBrandId(rs.getInt("brandId"));
                product.setScreenSize(rs.getFloat("screenSize"));
                product.setCreateDate(rs.getDate("createDate"));
                product.setUpdateDate(rs.getDate("updateDate"));
                product.setStatus(rs.getString("status"));

                // Populate ProductDetail object
                ProductDetail productDetails = new ProductDetail();
                productDetails.setId(rs.getInt("id"));  // Assuming id is the correct column for ProductDetail id
                productDetails.setProductId(rs.getInt("productId"));
                productDetails.setRamId(rs.getInt("ramId"));
                productDetails.setCpuId(rs.getInt("cpuId"));
                productDetails.setCardId(rs.getInt("cardId"));
                productDetails.setColor(rs.getString("color"));
                productDetails.setOriginPrice(rs.getDouble("originPrice"));
                productDetails.setSalePrice(rs.getDouble("salePrice"));
                productDetails.setQuantity(rs.getInt("quantity"));

                // Create ProductWithDetails and add to list
                ProductWithDetails productWithDetails = new ProductWithDetails(product, productDetails);
                productWithDetailsList.add(productWithDetails);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productWithDetailsList;
    }
    public int getQuantity(int product_id){
        String sql = "select quantity from [ProductDetail] where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ProductWithDetailsDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return 0;
    }
    public void changeQuantity(int id, int quantity){
        String sql = "UPDATE ProductDetail set quantity = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            int x = ps.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ProductWithDetailsDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }        
    
}
