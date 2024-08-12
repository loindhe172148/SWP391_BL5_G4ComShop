/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author xuant
 */
import entity.RAM;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RAMDAO extends ConnectDB {

    public List<RAM> getAllRAM() {
        List<RAM> ramList = new ArrayList<>();
        String sql = "SELECT * FROM RAM";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) { // Thực thi truy vấn
            while (rs.next()) {
                RAM ram = new RAM();
                ram.setId(rs.getInt("id"));
                ram.setType(rs.getString("type"));
                ram.setCapacity(rs.getInt("capacity"));
                ram.setSlot(rs.getInt("slot"));
                ram.setMaxSupport(rs.getInt("max support"));
                ramList.add(ram);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RAMDAO.class.getName()).log(Level.SEVERE, null, ex); // Ghi log lỗi
        }
        return ramList; // Trả về danh sách các RAM
    }
}
