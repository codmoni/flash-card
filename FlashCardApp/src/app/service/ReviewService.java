package app.service;

import app.dao.CardDao;
import app.model.Card;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ReviewService {
    private final CardDao cardDao = new CardDao();
    private final int MAX_CARDS_PER_SESSION = 30;

    // 오늘 복습 대상 카드 가져오기 (최대 30개)
    public List<Card> getTodayReviewCards() throws SQLException {
        List<Card> allTodayCards = cardDao.getCardsForToday(); // 모든 복습 대상 카드
        return allTodayCards.stream()
                .limit(MAX_CARDS_PER_SESSION)
                .toList(); // 최대 30개 제한
    }

    // 초과한 복습 카드 가져오기
    public List<Card> getRemainingTodayCards() throws SQLException {
        List<Card> allTodayCards = cardDao.getCardsForToday();
        return allTodayCards.stream()
                .skip(MAX_CARDS_PER_SESSION)
                .toList(); // 30개 이후의 카드
    }

    // 오늘 복습 카드 업데이트
    public void processTodayReviewFeedback(int cardId, String feedback) throws SQLException {
        cardDao.updateReviewHistory(cardId, feedback);
    }

    // 덱 복습용 카드 가져오기 (랜덤)
    public List<Card> getDeckReviewCards(int deckId) throws SQLException {
        List<Card> deckCards = cardDao.getCardsByDeck(deckId);
        Collections.shuffle(deckCards); // 랜덤 정렬
        return deckCards;
    }
}
