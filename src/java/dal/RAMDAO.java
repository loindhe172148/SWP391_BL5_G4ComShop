
package dal;

import entity.RAM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RAMDAO extends DBContext<RAM> {
    public List<RAM> getAllRAM() {
        List<RAM> ramList = new ArrayList<>();
        String sql = "SELECT * FROM RAM";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                RAM ram = new RAM();
                ram.setId(rs.getInt("id"));
                ram.setName(rs.getString("name"));
                ram.setBrand(rs.getString("brand"));
                ram.setMemory(rs.getInt("memory"));
                ram.setSpeed(rs.getInt("speed"));
                ram.setDescription(rs.getString("description"));
                ramList.add(ram);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RAMDAO.class.getName()).log(Level.SEVERE, "Error fetching RAM list", ex);
        }
        return ramList;
    }
    public RAM getRAMById(int id) {
        RAM ram = null;
        String sql = "SELECT * FROM RAM WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ram = new RAM();
                    ram.setId(rs.getInt("id"));
                    ram.setName(rs.getString("name"));
                    ram.setBrand(rs.getString("brand"));
                    ram.setMemory(rs.getInt("memory"));
                    ram.setSpeed(rs.getInt("speed"));
                    ram.setDescription(rs.getString("description"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RAMDAO.class.getName()).log(Level.SEVERE, "Error fetching RAM by ID", ex);
        }
        return ram;
    }
}
