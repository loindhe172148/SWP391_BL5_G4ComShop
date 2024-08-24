/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDBContext extends DBContext<User> {

    public ArrayList<User> listAll() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "select * from [User]\n"
                    + "where accid not in (select id as[bb] from [Account] where role ='admin')";
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
                m.setStatus(rs.getString("status").toLowerCase());

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

    public void insert(int accid, String gmail, String address, int gender, String phone, Date dob, String status, String ava, String fullname, Date createDate) {
        String sql = """
                 INSERT INTO [dbo].[User]
                                 ([accid]
                                 ,[gmail]
                                 ,[address]
                                 ,[gender]
                                 ,[phone]
                                 ,[dob]
                                 ,[status]
                                 ,[fullname]
                                 ,[ava]
                                 ,[createdate])
                           VALUES
                                 (?,?,?,?,?,?,?,?,?,?)""";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accid);
            ps.setString(2, gmail);
            ps.setString(3, address);
            ps.setInt(4, gender);
            ps.setString(5, phone);
            ps.setDate(6, dob);
            ps.setString(7, status);
            ps.setString(9, ava);
            ps.setString(8, fullname);
            ps.setDate(10, createDate);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
            String sql = "SELECT u.id, u.accid, u.ava, u.fullname, u.gmail, u.address, u.gender, u.phone, u.dob, u.status, a.username, a.role FROM [Account] a JOIN [User] u ON a.id = u.accid WHERE u.id = ?";
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
                user.setName(rs.getString("fullname"));
                user.setAccount(account);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByID(int id) {
        AccountDBContext db = new AccountDBContext();
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
                user.setName(rs.getString(9));
                Account a = db.getAccountByID(rs.getInt(2));
                user.setAccount(a);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Boolean checkMailExist(int id, String email) {
        String sql = "SELECT [gmail] FROM dbo.[User] WHERE accid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String existingEmail = rs.getString("gmail");
                return email.equals(existingEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByAccountId(int accountId) {
        User user = null;
        try {
            String sql = "SELECT * FROM [User] WHERE accid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setGmail(rs.getString("gmail"));
                user.setDob(rs.getDate("dob"));
                user.setAddress(rs.getString("address"));
                user.setGender(rs.getInt("gender"));  // Đổi từ setBoolean thành setInt để tương thích với kiểu int
                user.setPhone(rs.getString("phone"));
                user.setStatus(rs.getString("status")); // Đổi từ setBoolean thành setString để tương thích với kiểu String
                user.setAva(rs.getString("ava")); // Đặt giá trị cho thuộc tính avatar
                user.setName(rs.getString("fullname")); // Đặt giá trị cho thuộc tính name

                // Thiết lập đối tượng Account
                Account account = new Account();
                account.setId(accountId);
                user.setAccount(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void deleteFromCart(int userId, int productDetailId) {
        String query = "DELETE FROM CartDetail WHERE customerid = ? AND productdetailid = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, productDetailId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findUserByEmailOrPhone(String key) {
        List<User> list = new ArrayList();
        StringBuilder sql = new StringBuilder("select * from [User] where accid not in (select id as[bb] from [Account] where role ='admin') and gmail like ");
        sql.append(" '%").append(key).append("%' ");
        sql.append("or phone like '%").append(key).append("%' ");
        try {
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setGmail(rs.getString("gmail"));
                user.setDob(rs.getDate("dob"));
                user.setAddress(rs.getString("address"));
                user.setGender(rs.getInt("gender"));  // Đổi từ setBoolean thành setInt để tương thích với kiểu int
                user.setPhone(rs.getString("phone"));
                user.setStatus(rs.getString("status")); // Đổi từ setBoolean thành setString để tương thích với kiểu String
                user.setAva(rs.getString("ava")); // Đặt giá trị cho thuộc tính avatar
                user.setName(rs.getString("fullname")); // Đặt giá trị cho thuộc tính name

                // Thiết lập đối tượng Account
                Account account = new Account();
                account.setId(rs.getInt(2));
                user.setAccount(account);
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void createUser(User u) {
        String sql = """
                 INSERT INTO [dbo].[User]
                                 ([accid]
                                 ,[gmail]
                                 ,[address]
                                 ,[gender]
                                 ,[phone]
                                 ,[dob]
                                 ,[status]
                                 ,[fullname]
                                 ,[ava]
                                 ,[createdate])
                           VALUES
                                 (?,?,?,?,?,?,?,?,?,?)""";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, u.getAccount().getId());
            ps.setString(2, u.getGmail());
            ps.setString(3, u.getAddress());
            ps.setInt(4, u.getGender());
            ps.setString(5, u.getPhone());
            ps.setDate(6, new java.sql.Date(u.getDob().getTime()));
            ps.setString(7, "active");
            ps.setString(9, "null");
            ps.setString(8, u.getName());
            ps.setDate(10, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateStatusByID(int id, String status) {
        String sql = "update [User]\n"
                + "set status = ?\n"
                + "where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(2, id);
            ps.setString(1, status);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        UserDBContext db = new UserDBContext();
        User u = db.getUserByID(10);
        System.out.println(u.getName());
    }
}

