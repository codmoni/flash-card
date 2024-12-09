package app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import app.model.Card;
import app.model.ReviewHistory;

public class CardDao {
	//1. 카드 추가
	public void addCard(int deckId, String question, String answer, String additionalInfo) throws SQLException {
		String query = "INSERT INTO Cards (deck_id, question, answer, additional_info) VALUES (?, ?, ?,  ?)";
		try (Connection connection = DatabaseConnection.getConnection();
			PreparedStatement stmt = connection.prepareStatement(query)){
			stmt.setInt(1, deckId);
			stmt.setString(2, question);
            stmt.setString(3, answer);
            stmt.setString(4, additionalInfo);
            stmt.executeUpdate();
            System.out.println("카드 추가 완료!");
		}
	}
	
	//2. 카드 조회 (전체 조회)
	public List<String> getAllCards() throws SQLException {
		String query = "SELECT * FROM Cards";
		List<String> cards = new ArrayList<>();
		try(Connection connection = DatabaseConnection.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query)) {
			while(rs.next()) {
				cards.add("ID: " + rs.getInt("card_id") +
						", Question: " + rs.getString("question") +
						", Answer: " + rs.getString("answer"));
			}
		}
		return cards;
	}
	
	//3. 카드 수정
	public void updateCard(int cardId, String question, String answer, String additionalInfo) throws SQLException{
		String query = "UPDATE Cards SET question = ?, answer = ?, additional_info = ? WHERE card_id = ?";
		try(Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(query)){
			stmt.setString(1, question);
            stmt.setString(2, answer);
            stmt.setString(3, additionalInfo);
            stmt.setInt(4, cardId);
            stmt.executeUpdate(); // UPDATE 실행
            System.out.println("카드가 성공적으로 수정되었습니다!");
		}
	}
	
	//4. 카드 삭제
	public void deleteCard(int cardId) throws SQLException {
		String query = "DELETE FROM Cards WHERE card_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cardId);
            stmt.executeUpdate(); // DELETE 실행
            System.out.println("카드가 성공적으로 삭제되었습니다!");
        }
	}
	
	//5. 오늘의 복습 카드 조회
	public List<Card> getCardsForToday() throws SQLException{
		String query = """
				SELECT c.card_id, c.question, c.answer, c.additional_info
				FROM Cards c
				JOIN ReviewHistory r ON c.card_id = r.card_id
				WHERE DATE_ADD(r.review_date, INTERVAL r.review_interval DAY) = CURRENT_DATE
				""";
		List<Card> cards = new ArrayList<>();
		try(Connection connection = DatabaseConnection.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query)){
			while (rs.next()) {
				cards.add(new Card(
						rs.getInt("card_id"),
						rs.getInt("deck_id"),
						rs.getString("question"),
						rs.getString("answer"),
						rs.getString("additional_info"),
						rs.getTimestamp("created_at")
				));
			}
		}
		return cards;
	}
	
	//6. Review History 테이블 업데이트
	public void updateReviewHistory(int cardId, String feedback) throws SQLException {
		String query = """
				UPDATE ReviewHistory
				SET review_date = CURRENT_DATE,
					review_interval = CASE feedback
						WHEN 'Easy' THEN review_interval * 2
						WHEN 'Medium' THEN review_interval + 1
						WHEN 'Hard' THEN review_interval
						WHEN 'Fail' THEN GREATEST(review_interval/2, 1)
					END,
					retention_score = CASE feedback
						WHEN 'Easy' THEN LEAST(retention_score + 0.1, 1)
						WHEN 'Medium' THEN LEAST(retention_score + 0.05, 1)
						WHEN 'Hard' THEN LEAST(retention_score - 0.1, 0)
						WHEN 'Fail' THEN LEAST(retention_score - 0.2, 0)
					END,
					strike = CASE feedback
						WHEN ?= 'Easy' OR ?= 'Medium' THEN strike + 1
						WHEN ?= 'Fail' THEN GREATEST(strike-1, 0)
						ELSE strike
					END
				WHERE card_id = ?
				""";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
        		stmt.setInt(1, cardId);
        		stmt.executeUpdate();
           }
	}
	
	//7. 특정 덱의 카드 조회
	public List<Card> getCardsByDeck(int deckId) throws SQLException {
        String query = "SELECT * FROM Cards WHERE deck_id = ?";
        List<Card> cards = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, deckId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cards.add(new Card(
                            rs.getInt("card_id"),
                            rs.getInt("deck_id"),
                            rs.getString("question"),
                            rs.getString("answer"),
                            rs.getString("additional_info"),
                            rs.getTimestamp("created_at")
                    ));
                }
            }
        }
        return cards;
    }
	
	//8. 특정 덱의 카드 조회 (Retention Score 낮은 순 + 생성일 순)
	public List<Card> getCardsByDeckOrdered(int deckId) throws SQLException {
	    String query = """
	        SELECT c.card_id, c.deck_id, c.question, c.answer, c.additional_info, c.created_at
	        FROM Cards c
	        WHERE c.deck_id = ?
	        ORDER BY c.created_at DESC
	        """;
	    List<Card> cards = new ArrayList<>();
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(query)) {
	        stmt.setInt(1, deckId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                cards.add(new Card(
	                    rs.getInt("card_id"),
	                    rs.getInt("deck_id"),
	                    rs.getString("question"),
	                    rs.getString("answer"),
	                    rs.getString("additional_info"),
	                    rs.getTimestamp("created_at")
	                ));
	            }
	        }
	    }
	    return cards;
	}

}






