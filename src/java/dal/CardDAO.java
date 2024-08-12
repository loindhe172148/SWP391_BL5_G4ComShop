/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Card;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CardDAO extends ConnectDB {

    public List<Card> getAllCard() {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM Card";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) { // Thực thi truy vấn
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
}

