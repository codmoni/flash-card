package app.view;

import app.controller.ReviewManager;
import app.model.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReviewPanel extends JPanel {
    private final ReviewManager reviewManager = new ReviewManager();

    public ReviewPanel(boolean isTodayReview, int deckId) {
        setLayout(new BorderLayout());

        JPanel cardPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        List<Card> cards = isTodayReview
                ? reviewManager.getTodayReviewCards()
                : reviewManager.getDeckReviewCards(deckId);

        for (Card card : cards) {
            JLabel questionLabel = new JLabel(card.getQuestion(), SwingConstants.CENTER);
            JButton showAnswerButton = new JButton("답변 보기");
            showAnswerButton.addActionListener(e -> questionLabel.setText(card.getAnswer()));

            JPanel cardItem = new JPanel(new BorderLayout());
            cardItem.add(questionLabel, BorderLayout.CENTER);

            if (isTodayReview) {
                JPanel feedbackPanel = new JPanel(new GridLayout(1, 4));
                String[] feedbacks = {"Easy", "Medium", "Hard", "Fail"};
                for (String feedback : feedbacks) {
                    JButton feedbackButton = new JButton(feedback);
                    feedbackButton.addActionListener(e -> {
                        reviewManager.processReviewFeedback(card.getId(), feedback);
                        JOptionPane.showMessageDialog(this, "피드백 저장 완료: " + feedback);
                    });
                    feedbackPanel.add(feedbackButton);
                }
                cardItem.add(feedbackPanel, BorderLayout.SOUTH);
            }

            cardPanel.add(cardItem);
        }

        if (isTodayReview && reviewManager.getRemainingTodayCards().size() > 0) {
            add(new JLabel("남은 복습 카드가 존재합니다!"), BorderLayout.SOUTH);
        }

        add(new JScrollPane(cardPanel), BorderLayout.CENTER);
    }
}
