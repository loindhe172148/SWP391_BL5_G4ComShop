package dal;

import entity.UserAccount;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountDBContext extends DBContext<UserAccount> {

    public UserAccount getEmailandUsername(String input) {
        UserAccount uc = new UserAccount();
        String sql = "SELECT a.username, u.gmail FROM Account a JOIN [User] u ON a.id = u.accid WHERE a.username = ? OR u.gmail = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, input);
            ps.setString(2, input);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    uc.setMail(rs.getString("gmail"));
                    uc.setUsername(rs.getString("username"));
                    return uc;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
