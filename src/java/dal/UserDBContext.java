/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import entity.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
import java.sql.*;
import java.util.ArrayList;

public class UserDBContext extends DBContext<User> {

    @Override
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
                m.setBod(rs.getDate("dob"));
                m.setAddress(rs.getString("address"));
                m.setGender(rs.getInt("gender") == 1); // Assuming gender 1 = Male, 0 = Female
                m.setPhone(rs.getString("phone"));
                m.setStatus(rs.getString("status").equalsIgnoreCase("active"));
                // m.setAva(rs.getString("ava")); // Uncomment if the 'ava' column exists
                // m.setName(rs.getString("name")); // Uncomment if the 'name' column exists
                
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
    @Override
    public void insert(User entity) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [User](id, accid, ava, name, gmail, address, gender, phone, dob, status) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getId());
            ps.setInt(2, entity.getAccount().getId());
            ps.setString(3, entity.getAva());
            ps.setString(4, entity.getName());
            ps.setString(5, entity.getGmail());
            ps.setString(6, entity.getAddress());
            ps.setBoolean(7, entity.isGender());
            ps.setString(8, entity.getPhone());
            ps.setDate(9, new java.sql.Date(entity.getBod().getTime()));
            ps.setBoolean(10, entity.isStatus());
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e3) {
                e3.printStackTrace();
            }
        }
    }

    @Override
    public void update(User entity) {
        // Implement the general update method if needed
    }

    public void updateById(User entity, int id) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE [User] SET accid = ?, ava = ?, name = ?, gmail = ?, address = ?, gender = ?, phone = ?, dob = ?, status = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getAccount().getId());
            ps.setString(2, entity.getAva());
            ps.setString(3, entity.getName());
            ps.setString(4, entity.getGmail());
            ps.setString(5, entity.getAddress());
            ps.setBoolean(6, entity.isGender());
            ps.setString(7, entity.getPhone());
            ps.setDate(8, new java.sql.Date(entity.getBod().getTime()));
            ps.setBoolean(9, entity.isStatus());
            ps.setInt(10, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e3) {
                e3.printStackTrace();
            }
        }
    }

    @Override
    public void delete(User entity) {
        throw new UnsupportedOperationException("Not supported yet.");
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
                user.setBod(rs.getDate("dob"));
                user.setAddress(rs.getString("address"));
                user.setGender(rs.getBoolean("gender"));
                user.setPhone(rs.getString("phone"));
                user.setStatus(rs.getBoolean("status"));
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
}

