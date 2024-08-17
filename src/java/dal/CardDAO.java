/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Card;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CardDAO extends DBContext<Card> {

    public List<Card> getAllCard() {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM Card";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) { // Thực thi truy vấn
            while (rs.next()) {
                Card card = new Card();
                card.setId(rs.getInt("id"));
                card.setName(rs.getString("name"));
                cards.add(card);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CardDAO.class.getName()).log(Level.SEVERE, null, ex); // Ghi log lỗi
        }
        return cards; // Trả về danh sách các card
    }
     public List<Card> getCardAccessory() {
        List<Card> list = new ArrayList<>();
        String query = "SELECT * FROM Card WHERE isActive = 1"; // Only active Cards

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Card card = new Card(
                    rs.getInt("id"), 
                    rs.getString("name"), 
                    rs.getString("brand"), 
                    rs.getString("memory"), 
                    rs.getString("chipset"), 
                    rs.getString("description")
                );
                list.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Method to insert a new Card record
    public void insertCard(String name, String brand, String memory, String chipset, String description) {
        String query = "INSERT INTO [dbo].[Card] ([name], [brand], [memory], [chipset], [description]) VALUES\n" +
"(?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, brand);
            ps.setString(3, memory);
            ps.setString(4, chipset);
            ps.setString(5, description);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to soft delete a Card record (mark as inactive)
    public void softDeleteCard(int id) {
        String query = "UPDATE Card SET isActive = 0 WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

