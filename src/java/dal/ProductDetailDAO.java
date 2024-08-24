package dal;

import entity.ProductDetail;
import java.sql.*;

public class ProductDetailDAO extends DBContext<ProductDetail> {

    // Tâm
    public void addProductDetail(ProductDetail productDetail) throws SQLException {
        String sql = "INSERT INTO ProductDetail (productId, ramId, cpuId, cardId, color, originPrice, salePrice, quantity) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productDetail.getProductId());
            stmt.setInt(2, productDetail.getRamId());
            stmt.setInt(3, productDetail.getCpuId());
            stmt.setInt(4, productDetail.getCardId());
            stmt.setString(5, productDetail.getColor());
            stmt.setDouble(6, productDetail.getOriginPrice());
            stmt.setDouble(7, productDetail.getSalePrice());
            stmt.setInt(8, productDetail.getQuantity());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

}

