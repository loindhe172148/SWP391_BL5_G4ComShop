/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class AccountDBContext extends DBContext<Account> {

    public ArrayList<Account> listAll() {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Account";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("pass"));
                a.setRole(rs.getString("role"));
                accounts.add(a);
            }
        } catch (SQLException e) {
        }
        return accounts;
    }

    public void insert(Account entity) {
        try {

            String sql = "INSERT INTO Account (username, pass, role) VALUES(?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void updateById(Account entity, int id) {
        try {
            String sql = "UPDATE Account SET username = ?, pass = ?, role = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getRole());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void delete(Account entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Account checkAccountExist(String username, String email) {
        try {
            String sql = "SELECT * FROM Account WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("pass"));
                a.setRole(rs.getString("role"));
                return a;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public Account checkAccountExist(String username) {
        String sql = "SELECT * FROM Account WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account();
                    account.setId(rs.getInt("id"));
                    return account;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Account getAccount(String username, String password) {
        try {
            String sql = "SELECT * FROM Account WHERE username = ? AND pass = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("pass"));
                a.setRole(rs.getString("role"));
                return a;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public int getAccountIDByUsername(String username) {
        int id = 0;
        try {
            String sql = "SELECT id FROM Account WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
        }
        return id;
    }

    public void updatePasswordByUsername(String password, String username) {
        try {
            String sql = "UPDATE Account SET pass = ? WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public String getPassbyUsername(String username) {
        String pass = "";
        String sql = "SELECT [pass] from Account WHERE username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pass = rs.getString("pass");
            }
        } catch (SQLException e) {

        }
        return pass;
    }

    public Account getAccountByID(int id) {
        try {
            String sql = "SELECT * FROM Account WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("pass"));
                a.setRole(rs.getString("role"));
                return a;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void updateRoleByUsername(String username, String role) {
        String sql =  " update Account \n"
                + " set role = ?\n"
                + " where username = ?\n"
                + "";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, role);
            ps.setString(2, username);
            int x = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
