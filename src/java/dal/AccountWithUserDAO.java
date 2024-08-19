package dal;

import entity.Account;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AccountWithUserDAO extends DBContext<Account> {

    public int getAccountWithUserCount(String role, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT COUNT(*) FROM Account a "
                + "JOIN [User] u ON a.id = u.accid "
                + "WHERE u.createdate BETWEEN ? AND ?";

        // Add status filtering if status is provided
        if (role != null) {
            sql += " AND a.role = ?";
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            if (role != null) {
                stmt.setString(3, role);
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
