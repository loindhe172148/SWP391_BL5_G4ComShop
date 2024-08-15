/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author xuant
 */
import entity.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TypeDAO extends DBContext<Type> {

    public List<Type> getAllType() {
        List<Type> typeList = new ArrayList<>();
        String sql = "SELECT * FROM Type";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) { // Thực thi truy vấn
            while (rs.next()) {
                Type type = new Type();
                type.setId(rs.getInt("id"));
                type.setTypeName(rs.getString("typename"));
                typeList.add(type);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeDAO.class.getName()).log(Level.SEVERE, null, ex); // Ghi log lỗi
        }
        return typeList; // Trả về danh sách các type
    }
}
