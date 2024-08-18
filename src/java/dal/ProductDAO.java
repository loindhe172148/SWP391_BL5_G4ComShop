package dal;

import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

public class ProductDAO extends DBContext<Product> {

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
}
