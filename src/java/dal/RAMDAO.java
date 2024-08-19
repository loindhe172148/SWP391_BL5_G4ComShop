
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
        public List<RAM> getRamAccessory(){
        List<RAM> list = new ArrayList<>();
        String query = "SELECT * FROM RAM WHERE isActive = 1"; // Only active RAMs

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RAM ram = new RAM(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("brand"),
                    rs.getInt("memory"),
                    rs.getInt("speed"),
                    rs.getString("description")
                );
                list.add(ram);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Method to retrieve all active RAM records


    // Method to insert a new RAM record
    public void insertRam(String name, String brand, int memory, int speed, String description) {
        String query = "INSERT INTO RAM ([name], [brand], [memory], [speed], [description]) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, brand);
            ps.setInt(3, memory);
            ps.setInt(4, speed);
            ps.setString(5, description);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to soft delete a RAM record (mark as inactive)
    public void softDeleteRam(int id) {
        String query = "UPDATE RAM SET isActive = 0 WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
