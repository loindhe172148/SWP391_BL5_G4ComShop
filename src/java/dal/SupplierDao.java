/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Suppliere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class SupplierDao extends DBContext<Suppliere>{
      public List<Suppliere> getAllSuppliers() {
        List<Suppliere> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM Supplier";

       try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Suppliere supplier = new Suppliere();
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getString("name"));
                supplier.setEmail(rs.getString("email"));
                supplier.setPhone(rs.getString("phone"));
                supplier.setAddress(rs.getString("address"));

                suppliers.add(supplier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return suppliers;
    }
    
    
    
}
