package app.controller;

import app.model.Card;
import app.service.ReviewService;

import java.sql.SQLException;
import java.util.List;

public class ReviewManager {
    private final ReviewService reviewService = new ReviewService();

    // 오늘 복습 카드 가져오기
    public List<Card> getTodayReviewCards() {
        try {
            return reviewService.getTodayReviewCards();
        } catch (SQLException e) {
            System.err.println("오늘 복습 카드 조회 실패: " + e.getMessage());
            return List.of();
        }
    }

    // 남은 복습 카드 가져오기
    public List<Card> getRemainingTodayCards() {
        try {
            return reviewService.getRemainingTodayCards();
        } catch (SQLException e) {
            System.err.println("남은 복습 카드 조회 실패: " + e.getMessage());
            return List.of();
        }
    }

    // 덱 복습 카드 가져오기
    public List<Card> getDeckReviewCards(int deckId) {
        try {
            return reviewService.getDeckReviewCards(deckId);
        } catch (SQLException e) {
            System.err.println("덱 복습 카드 조회 실패: " + e.getMessage());
            return List.of();
        }
    }

    // 피드백 처리
    public void processReviewFeedback(int cardId, String feedback) {
        try {
            reviewService.processTodayReviewFeedback(cardId, feedback);
        } catch (SQLException e) {
            System.err.println("피드백 처리 실패: " + e.getMessage());
        }
    }
}
