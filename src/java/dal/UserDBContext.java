/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDBContext extends DBContext<User> {

      public ArrayList<User> listAll() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [User]";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User m = new User();
                m.setId(rs.getInt("id"));
                m.setGmail(rs.getString("gmail"));
                m.setdob(rs.getDate("dob"));
                m.setAddress(rs.getString("address"));
                m.setGender(rs.getInt("gender")); 
                m.setPhone(rs.getString("phone"));
                m.setStatus(rs.getString("status"));
                
                Account account = new Account();
                account.setId(rs.getInt("accid"));
                m.setAccount(account);                  
                users.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
      
    public void insert(User entity) {
        try {
            String sql = "INSERT INTO [User]( accid, ava, name, mail, address, gender, phone, dob, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getAccount().getId());
            ps.setString(2, entity.getAva());
            ps.setString(3, entity.getName());
            ps.setString(4, entity.getGmail());
            ps.setString(5, entity.getAddress());
            ps.setInt(6, entity.getGender());
            ps.setString(7, entity.getPhone());
            ps.setDate(8, new java.sql.Date(entity.getDob().getTime()));
            ps.setString(9, entity.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {          
        }
    }

    public void updateById(User entity, int id) {
        try {
            String sql = "UPDATE [User] SET ava = ?, name = ?, gmail = ?, address = ?, gender = ?, phone = ?, dob = ?, status = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getAva());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getGmail());
            ps.setString(4, entity.getAddress());
            ps.setInt(5, entity.getGender());
            ps.setString(6, entity.getPhone());
            ps.setDate(7, new java.sql.Date(entity.getDob().getTime()));
            ps.setString(8, entity.getStatus());
            ps.setInt(9, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
        }
    }

    public User getUserById(int id) {
        try {
            String sql = "SELECT u.id, u.accid, u.ava, u.name, u.gmail, u.address, u.gender, u.phone, u.dob, u.status, a.username, a.role FROM [Account] a JOIN [User] u ON a.id = u.accid WHERE u.id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("accid"));
                account.setUsername(rs.getString("username"));
                account.setRole(rs.getString("role"));
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setGmail(rs.getString("gmail"));
                user.setdob(rs.getDate("dob"));
                user.setAddress(rs.getString("address"));
                user.setGender(rs.getInt("gender"));
                user.setPhone(rs.getString("phone"));
                user.setStatus(rs.getString("status"));
                user.setAva(rs.getString("ava"));
                user.setName(rs.getString("name"));
                user.setAccount(account);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User getUserByID(int id) {
        User user = null;
        try {
            String sql = "SELECT * FROM [User] WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
               
                user.setGmail(rs.getString("gmail"));
                user.setdob(rs.getDate("dob"));
                user.setAddress(rs.getString("address"));
                user.setGender(rs.getInt("gender"));
                user.setPhone(rs.getString("phone"));
                user.setStatus(rs.getString("status"));
               

                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return user;
    }
}