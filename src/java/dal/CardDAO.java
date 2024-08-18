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

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Card card = new Card();
                card.setId(rs.getInt("id"));
                card.setName(rs.getString("name"));
                card.setBrand(rs.getString("brand"));
                card.setMemory(rs.getInt("memory"));
                card.setChipset(rs.getString("chipset"));
                card.setDescription(rs.getString("description"));
                cards.add(card);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CardDAO.class.getName()).log(Level.SEVERE, "Error fetching card list", ex);
        }
        return cards;
    }

    public Card getCardById(int id) {
        Card card = null;
        String sql = "SELECT * FROM Card WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    card = new Card();
                    card.setId(rs.getInt("id"));
                    card.setName(rs.getString("name"));
                    card.setBrand(rs.getString("brand"));
                    card.setMemory(rs.getInt("memory"));
                    card.setChipset(rs.getString("chipset"));
                    card.setDescription(rs.getString("description"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CardDAO.class.getName()).log(Level.SEVERE, "Error fetching card by id", ex);
        }

        return card;
    }
}
