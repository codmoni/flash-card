package app.service;

import app.dao.CardDao;
import app.dao.DeckDao;
import app.model.Card;
import app.model.Deck;

import java.sql.SQLException;
import java.util.List;

public class DataService {
    private final DeckDao deckDao = new DeckDao();
    private final CardDao cardDao = new CardDao();

    // 덱 전체 조회 (최신순)
    public List<Deck> getAllDecks() throws SQLException {
        return deckDao.getAllDecksOrderedByCreatedAt();
    }

    // 특정 덱의 카드 조회 (Retention Score 낮은 순 + 생성일 순)
    public List<Card> getCardsByDeck(int deckId) throws SQLException {
        return cardDao.getCardsByDeckOrdered(deckId);
    }

    // 카드 추가
    public void addCard(int deckId, String question, String answer, String additionalInfo) throws SQLException {
        cardDao.addCard(deckId, question, answer, additionalInfo);
    }

    // 카드 수정
    public void updateCard(int cardId, String question, String answer, String additionalInfo) throws SQLException {
        cardDao.updateCard(cardId, question, answer, additionalInfo);
    }

    // 카드 삭제
    public void deleteCard(int cardId) throws SQLException {
        cardDao.deleteCard(cardId);
    }

    // 덱 추가
    public void addDeck(int userId, String name) throws SQLException {
        deckDao.addDeck(userId, name);
    }

    // 덱 수정
    public void updateDeck(int deckId, String name) throws SQLException {
        deckDao.updateDeck(deckId, name);
    }

    // 덱 삭제
    public void deleteDeck(int deckId) throws SQLException {
        deckDao.deleteDeck(deckId);
    }
}
