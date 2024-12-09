package app.view;

import app.controller.DataManager;
import app.model.Card;
import app.model.Deck;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DataManagementPanel extends JPanel {
	//UserId 임시 사용하는 부분
	
    private final DataManager dataManager = new DataManager();

    public DataManagementPanel() {
        setLayout(new BorderLayout());
        initializeDeckPanel();
    }

    private void initializeDeckPanel() {
        removeAll();
        JPanel deckPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        List<Deck> decks = dataManager.getAllDecks();

        for (Deck deck : decks) {
            JPanel deckItem = new JPanel(new BorderLayout());
            JButton deckButton = new JButton(deck.getName());
            deckButton.addActionListener(e -> initializeCardPanel(deck.getId()));

            JButton deleteButton = new JButton("삭제");
            deleteButton.addActionListener(e -> {
                dataManager.deleteDeck(deck.getId());
                initializeDeckPanel();
            });

            deckItem.add(deckButton, BorderLayout.CENTER);
            deckItem.add(deleteButton, BorderLayout.EAST);
            deckPanel.add(deckItem);
        }

        JButton addDeckButton = new JButton("덱 추가");
        addDeckButton.addActionListener(e -> {
            String deckName = JOptionPane.showInputDialog(this, "덱 이름 입력");
            dataManager.addDeck(1, deckName); // User ID 임시 사용
            initializeDeckPanel();
        });

        add(new JScrollPane(deckPanel), BorderLayout.CENTER);
        add(addDeckButton, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    private void initializeCardPanel(int deckId) {
        removeAll();
        JPanel cardPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        List<Card> cards = dataManager.getCardsByDeck(deckId);

        for (Card card : cards) {
            JPanel cardItem = new JPanel(new BorderLayout());
            JLabel questionLabel = new JLabel(card.getQuestion(), SwingConstants.CENTER);

            JButton editButton = new JButton("수정");
            editButton.addActionListener(e -> {
                String question = JOptionPane.showInputDialog(this, "질문 수정", card.getQuestion());
                String answer = JOptionPane.showInputDialog(this, "답변 수정", card.getAnswer());
                String additionalInfo = JOptionPane.showInputDialog(this, "추가 정보 수정", card.getAdditionalInfo());
                dataManager.updateCard(card.getId(), question, answer, additionalInfo);
                initializeCardPanel(deckId);
            });

            JButton deleteButton = new JButton("삭제");
            deleteButton.addActionListener(e -> {
                dataManager.deleteCard(card.getId());
                initializeCardPanel(deckId);
            });

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
            buttonPanel.add(editButton);
            buttonPanel.add(deleteButton);

            cardItem.add(questionLabel, BorderLayout.CENTER);
            cardItem.add(buttonPanel, BorderLayout.SOUTH);
            cardPanel.add(cardItem);
        }

        JButton addCardButton = new JButton("카드 추가");
        addCardButton.addActionListener(e -> {
            String question = JOptionPane.showInputDialog(this, "질문 입력");
            String answer = JOptionPane.showInputDialog(this, "답변 입력");
            String additionalInfo = JOptionPane.showInputDialog(this, "추가 정보 입력");
            dataManager.addCard(deckId, question, answer, additionalInfo);
            initializeCardPanel(deckId);
        });

        add(new JScrollPane(cardPanel), BorderLayout.CENTER);
        add(addCardButton, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }
}
