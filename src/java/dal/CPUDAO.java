
package dal;

import entity.CPU;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CPUDAO extends DBContext<CPU> {

    public List<CPU> getAllCPU() {
        List<CPU> cpus = new ArrayList<>();
        String sql = "SELECT * FROM CPU";

        try (PreparedStatement ps = connection.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CPU cpu = new CPU();
                cpu.setId(rs.getInt("id"));
                cpu.setName(rs.getString("name"));
                cpu.setBrand(rs.getString("brand"));
                cpu.setGeneration(rs.getString("generation"));
                cpu.setDescription(rs.getString("description"));
                cpus.add(cpu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CPUDAO.class.getName()).log(Level.SEVERE, "Error fetching CPU list", ex);
        }
        return cpus;
    }
    public CPU getCPUById(int id) {
        CPU cpu = null;
        String sql = "SELECT * FROM CPU WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cpu = new CPU();
                    cpu.setId(rs.getInt("id"));
                    cpu.setName(rs.getString("name"));
                    cpu.setBrand(rs.getString("brand"));
                    cpu.setGeneration(rs.getString("generation"));
                    cpu.setDescription(rs.getString("description"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CPUDAO.class.getName()).log(Level.SEVERE, "Error fetching CPU by ID", ex);
        }
        return cpu;
    }
}

