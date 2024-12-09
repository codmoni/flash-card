package app.controller;

import app.model.Card;
import app.model.Deck;
import app.service.DataService;

import java.sql.SQLException;
import java.util.List;

public class DataManager {
    private final DataService dataService = new DataService();

    // 덱 전체 조회
    public List<Deck> getAllDecks() {
        try {
            return dataService.getAllDecks();
        } catch (SQLException e) {
            System.err.println("덱 조회 실패: " + e.getMessage());
            return List.of(); // 빈 리스트 반환
        }
    }

    // 특정 덱의 카드 조회
    public List<Card> getCardsByDeck(int deckId) {
        try {
            return dataService.getCardsByDeck(deckId);
        } catch (SQLException e) {
            System.err.println("카드 조회 실패: " + e.getMessage());
            return List.of();
        }
    }

    // 덱 추가
    public void addDeck(int userId, String name) {
        try {
            dataService.addDeck(userId, name);
        } catch (SQLException e) {
            System.err.println("덱 추가 실패: " + e.getMessage());
        }
    }

    // 덱 수정
    public void updateDeck(int deckId, String name) {
        try {
            dataService.updateDeck(deckId, name);
        } catch (SQLException e) {
            System.err.println("덱 수정 실패: " + e.getMessage());
        }
    }

    // 덱 삭제
    public void deleteDeck(int deckId) {
        try {
            dataService.deleteDeck(deckId);
        } catch (SQLException e) {
            System.err.println("덱 삭제 실패: " + e.getMessage());
        }
    }

    // 카드 추가
    public void addCard(int deckId, String question, String answer, String additionalInfo) {
        try {
            dataService.addCard(deckId, question, answer, additionalInfo);
        } catch (SQLException e) {
            System.err.println("카드 추가 실패: " + e.getMessage());
        }
    }

    // 카드 수정
    public void updateCard(int cardId, String question, String answer, String additionalInfo) {
        try {
            dataService.updateCard(cardId, question, answer, additionalInfo);
        } catch (SQLException e) {
            System.err.println("카드 수정 실패: " + e.getMessage());
        }
    }

    // 카드 삭제
    public void deleteCard(int cardId) {
        try {
            dataService.deleteCard(cardId);
        } catch (SQLException e) {
            System.err.println("카드 삭제 실패: " + e.getMessage());
        }
    }
}
