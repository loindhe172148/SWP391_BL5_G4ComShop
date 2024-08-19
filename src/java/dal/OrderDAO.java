
package dal;

import entity.Order;

import java.sql.*;
import java.time.LocalDate;

public class OrderDAO extends DBContext<Order> {

    public int getOrderCount(String status, LocalDate startDate, LocalDate endDate) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM [Order] WHERE orderdate BETWEEN ? AND ?");
        
        if (status != null && !status.isEmpty()) {
            sql.append(" AND statusid = ?");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            
            if (status != null && !status.isEmpty()) {
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
}

