/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.CPU;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xuant
 */
public class CPUDAO extends ConnectDB {

    public List<CPU> getAllCPU() {
        List<CPU> cpus = new ArrayList<>();
        String sql = "SELECT * FROM CPU";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CPU cpu = new CPU();
                cpu.setId(rs.getInt("id"));
                cpu.setName(rs.getString("name"));
                cpus.add(cpu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CPUDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cpus;
    }

}
