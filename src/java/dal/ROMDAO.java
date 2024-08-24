
package dal;

import entity.ROM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ROMDAO extends DBContext<ROM>{
    public List<ROM> getAllROM() {
        List<ROM> romList = new ArrayList<>();
        String sql = "SELECT * FROM ROM";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ROM rom = new ROM();
                rom.setId(rs.getInt("id"));
                rom.setCapacity(rs.getInt("capacity"));
                rom.setDescription(rs.getString("description"));
                romList.add(rom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ROMDAO.class.getName()).log(Level.SEVERE, "Error fetching ROM list", ex);
        }
        return romList;
    }
    public List<ROM> getROMsByProductId(int productId) {
    List<ROM> romList = new ArrayList<>();
    String sql = "SELECT DISTINCT r.id, r.capacity, r.description " +
                 "FROM ProductDetail pd " +
                 "JOIN ROM r ON pd.romid = r.id " +
                 "WHERE pd.productid = ? AND pd.romid IS NOT NULL";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, productId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ROM rom = new ROM();
                rom.setId(rs.getInt("id"));
                rom.setCapacity(rs.getInt("capacity"));
                rom.setDescription(rs.getString("description"));
                romList.add(rom);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(ROMDAO.class.getName()).log(Level.SEVERE, "Error fetching ROM list", ex);
    }
    return romList;
}

}

