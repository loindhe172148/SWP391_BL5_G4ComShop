package dal;

import entity.Product;
import entity.ProductDetail;
import entity.ProductWithDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductWithDetailsDAO extends DBContext<ProductWithDetails> {

    public List<ProductWithDetails> getProductWithDetails(int start, int pageSize, String search, String sortColumn, String sortOrder) {
        List<ProductWithDetails> products = new ArrayList<>();
        if (search.trim().equals("_")) {
                search = search.replace("_", "[_]");
            }
            if (search.trim().equals("%")) {
                search = search.replace("%", "[%]");
            }
        if (sortColumn == null || sortColumn.isEmpty()) {
            sortColumn = "pd.id"; // Default sorting column
        } else {
            switch (sortColumn) {
                case "id":
                case "originPrice":
                case "salePrice":
                    sortColumn = "pd." + sortColumn;
                    break;
                default:
                    sortColumn = "p." + sortColumn;
                    break;
            }
        }
        String query = "SELECT p.*, pd.* "
                + "FROM Product p "
                + "JOIN ProductDetail pd ON p.id = pd.productid "
                + "WHERE p.name LIKE ? "
                + "ORDER BY " + sortColumn + " " + sortOrder + " "
                + "OFFSET ? ROWS "
                + "FETCH NEXT ? ROWS ONLY;";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + search + "%");
            stmt.setInt(2, start);
            stmt.setInt(3, pageSize);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
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

                ProductDetail productDetails = new ProductDetail();
                productDetails.setId(rs.getInt(13));
                productDetails.setProductId(rs.getInt("productId"));
                productDetails.setRamId(rs.getInt("ramId"));
                productDetails.setCpuId(rs.getInt("cpuId"));
                productDetails.setCardId(rs.getInt("cardId"));
                productDetails.setOriginPrice(rs.getDouble("originPrice"));
                productDetails.setSalePrice(rs.getDouble("salePrice"));

                products.add(new ProductWithDetails(product, productDetails));
            }

        } catch (SQLException e) {
            e.printStackTrace();
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

}
