
package dal;

import entity.Brand;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrandDAL extends DBContext<Brand>{
    public List<Brand> getAllBrands() {
        List<Brand> brandList = new ArrayList<>();
        String sql = "SELECT * FROM Brand";
        try (PreparedStatement ps = connection.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setId(rs.getInt("id"));
                brand.setName(rs.getString("name"));
                brand.setDescription(rs.getString("description"));
                brand.setIsActive(rs.getBoolean("isActive"));
                brandList.add(brand);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAL.class.getName()).log(Level.SEVERE, "Error fetching brand list", ex);
        }
        return brandList;
    }

    // Lấy thương hiệu theo id
    public Brand getBrandById(int id) {
        Brand brand = null;
        String sql = "SELECT * FROM Brand WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    brand = new Brand();
                    brand.setId(rs.getInt("id"));
                    brand.setName(rs.getString("name"));
                    brand.setDescription(rs.getString("description"));
                    brand.setIsActive(rs.getBoolean("isActive"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAL.class.getName()).log(Level.SEVERE, "Error fetching brand by ID", ex);
        }
        return brand;
    }
}

