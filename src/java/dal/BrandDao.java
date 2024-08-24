package dal;

import entity.Brandname;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandDao extends DBContext<Brandname> {
    public List<Brandname> getBrandnameAccessory() {
        List<Brandname> list = new ArrayList<>();
        String query = "SELECT * FROM Brand WHERE isActive =1";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Brandname brandname = new Brandname(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
                list.add(brandname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void insertBrandname(String brandName, String description) {

    String query = "INSERT INTO [dbo].[Brand] ([name], [description]) VALUES (?, ?)";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        // Set the values for the placeholders
        ps.setString(1, brandName);
        ps.setString(2, description);

       
        ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace(); 
    } finally {
        // Close the connection (optional in this case since we're using try-with-resources)
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    public void softDeleteBrand(int id) {
    String query = "UPDATE Brand SET isActive = 0 WHERE id = ?";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
   



}

