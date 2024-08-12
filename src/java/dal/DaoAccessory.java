/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Accessory;
import entity.Brandname;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class DaoAccessory {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Accessory> getMaketingAccessory() {
        List<Accessory> list = new ArrayList<>();
        String query = "SELECT * FROM [dbo].[Accessory]";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Accessory accessory = new Accessory(
                        rs.getInt(1), // Assuming 'id' is an integer column
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10), // This seems to be a typo; it should probably be "switch"
                        rs.getString(11),
                        rs.getFloat(12),
                        rs.getFloat(13),
                        rs.getInt(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17)
                );
                list.add(accessory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Brandname> getBrandnameAccessory() {
        List<Brandname> list = new ArrayList<>();
        String query = "SELECT * FROM BrandName"; // Ensure 'BrandName' is the correct table name

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Brandname brandname = new Brandname(rs.getInt(1), rs.getString(2));
                list.add(brandname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Accessory> getFillterAccessory(String idBrandName) {
        List<Accessory> list = new ArrayList<>();
        String query = "SELECT * FROM [dbo].[Accessory] \n"
                + "WHERE brandname = ?"; // Corrected the query format

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idBrandName); // Set the value of idBrandName

            rs = ps.executeQuery();

            while (rs.next()) {
                Accessory accessory = new Accessory(
                        rs.getInt(1), // Assuming 'id' is an integer column
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10), // This seems to be a typo; it should probably be "switch"
                        rs.getString(11),
                        rs.getFloat(12),
                        rs.getFloat(13),
                        rs.getInt(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17)
                );
                list.add(accessory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Accessory> getSearchAccessory(String txtSearch) {
        List<Accessory> list = new ArrayList<>();
        String query = "SELECT * FROM [dbo].[Accessory] \n"
                + "WHERE [name] like ?"; // Corrected the query format

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%"); // Set the value of idBrandName

            rs = ps.executeQuery();

            while (rs.next()) {
                Accessory accessory = new Accessory(
                        rs.getInt(1), // Assuming 'id' is an integer column
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10), // This seems to be a typo; it should probably be "switch"
                        rs.getString(11),
                        rs.getFloat(12),
                        rs.getFloat(13),
                        rs.getInt(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17)
                );
                list.add(accessory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void deleteAccessory(String Aid) {
        String query = "DELETE FROM Accessory WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, Aid); // Set the accessory ID
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
public void insertAccessory(String name, String brandName, String status, String description,
                          String capacity, String connect, String compatible, String color,
                          String dpi, String layout, String switcha, String feature,
                          String originPrice, String salePrice, String quantity, String img) {

    String query = "INSERT INTO [dbo].[Accessory] ([name], [brandname], [status], [description], " +
                   "[capacity], [connect], [compatible], [color], [dpi], [layout], [switch], " +
                   "[feature], [originprice], [saleprice], [quantity], [img]) " +
                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);

        // Set the values for the placeholders
        ps.setString(1, name);
        ps.setString(2, brandName);
        ps.setString(3, status);
        ps.setString(4, description);
        ps.setString(5, capacity);
        ps.setString(6, connect);
        ps.setString(7, compatible);
        ps.setString(8, color);
        ps.setString(9, dpi);
        ps.setString(10, layout);
        ps.setString(11, switcha);
        ps.setString(12, feature);
        ps.setFloat(13, Float.parseFloat(originPrice));
        ps.setFloat(14, Float.parseFloat(salePrice));
        ps.setInt(15, Integer.parseInt(quantity));
        ps.setString(16, img);

        ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace(); // Log the exception
    } finally {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        

        
        }
public Accessory getAccessoryById(String id) {
      Accessory accessory = null;
        String query = "SELECT * FROM Accessory WHERE id = ?";

        try {
            conn = new DBContext().getConnection(); // Lấy kết nối từ DBContext
            ps = conn.prepareStatement(query);
            ps.setString(1, id); // Đặt giá trị cho tham số đầu tiên (id)
            rs = ps.executeQuery();

            if (rs.next()) {
                accessory = new Accessory(
                        rs.getInt(1), // Assuming 'id' is an integer column
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10), // This seems to be a typo; it should probably be "switch"
                        rs.getString(11),
                        rs.getFloat(12),
                        rs.getFloat(13),
                        rs.getInt(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return accessory;
    }



               
      

    public static void main(String[] args) {
        DaoAccessory dao = new DaoAccessory();
        List<Accessory> list = dao.getFillterAccessory("2");
        List<Brandname> listb = dao.getBrandnameAccessory();
        for (Accessory o : list) {
            System.out.println(o);
        }
    }
}
