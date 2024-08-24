
package dal;

import entity.ProductType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductTypeDAO extends DBContext<ProductType> {

    public List<ProductType> getAllProductType() {
        List<ProductType> typeList = new ArrayList<>();
        String sql = "SELECT * FROM ProductType";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) { // Thực thi truy vấn
            while (rs.next()) {
                ProductType type = new ProductType();
                type.setId(rs.getInt("id"));
                type.setTypeName(rs.getString("name"));
                typeList.add(type);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex); // Ghi log lỗi
        }
        return typeList; 
    }
    
}

