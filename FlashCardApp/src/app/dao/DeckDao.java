package app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import app.model.Deck;

public class DeckDao {
    // 1. 덱 추가
    public void addDeck(int userId, String name) throws SQLException {
        String query = "INSERT INTO Decks (user_id, name) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, name);
            stmt.executeUpdate();
            System.out.println("덱이 성공적으로 추가되었습니다!");
        }
    }

    // 2. 덱 조회 (전체 조회)
    public List<Deck> getAllDecks() throws SQLException {
        String query = "SELECT * FROM Decks";
        List<Deck> decks = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
            	int deckId = rs.getInt("deck_id");
                String name = rs.getString("name");
                int userId = rs.getInt("user_id");
                Timestamp createdAt = rs.getTimestamp("created_at");
                decks.add(new Deck(deckId, userId, name, createdAt));
            }
        }
        return decks;
    }

    // 3. 덱 수정
    public void updateDeck(int deckId, String newName) throws SQLException {
        String query = "UPDATE Decks SET name = ? WHERE deck_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setInt(2, deckId);
            stmt.executeUpdate();
            System.out.println("덱이 성공적으로 수정되었습니다!");
        }
    }

    // 4. 덱 삭제
    public void deleteDeck(int deckId) throws SQLException {
        String query = "DELETE FROM Decks WHERE deck_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, deckId);
            stmt.executeUpdate();
            System.out.println("덱이 성공적으로 삭제되었습니다!");
        }
    }
    
    //5. 덱 전체 조회(orderByCreatedAt)
    public List<Deck> getAllDecksOrderedByCreatedAt() throws SQLException {
        String query = "SELECT * FROM Decks ORDER BY created_at DESC";
        List<Deck> decks = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
            	int deckId = rs.getInt("deck_id");
                String name = rs.getString("name");
                int userId = rs.getInt("user_id");
                Timestamp createdAt = rs.getTimestamp("created_at");
                decks.add(new Deck(deckId, userId, name, createdAt));
            }
        }
        return decks;
    }
}
