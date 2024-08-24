
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
    //Tam
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
    
    //Linh - ko sua cua nhau
    public List<CPU> getCpuAccessory() {
        List<CPU> list = new ArrayList<>();
        String query = "SELECT * FROM Cpu WHERE isActive = 1"; // Only active CPUs

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CPU cpu = new CPU(rs.getInt("id"), rs.getString("name"),rs.getString("brand"), rs.getString("generation"), rs.getString("description"));
                list.add(cpu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Method to insert a new CPU record
    public void insertCpu(String name,String brand, String generation, String description) {
        String query = "INSERT INTO Cpu ([name],[brand], [generation], [description]) VALUES (?, ?, ?,?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, brand);
            ps.setString(3, generation);
            ps.setString(4, description);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to soft delete a CPU record (mark as inactive)
    public void softDeleteCpu(int id) {
        String query = "UPDATE Cpu SET isActive = 0 WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}


