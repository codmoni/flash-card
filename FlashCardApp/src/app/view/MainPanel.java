package app.view;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel(JFrame mainFrame) {
        setLayout(new BorderLayout());

        // 복습 화면 버튼
        JButton reviewTodayButton = new JButton("오늘의 복습");
        reviewTodayButton.addActionListener(e -> {
            // ReviewPanel의 생성자를 호출 (오늘의 복습: isTodayReview = true)
            mainFrame.setContentPane(new ReviewPanel(true, 0));
            mainFrame.revalidate();
        });

        JButton deckReviewButton = new JButton("덱 복습");
        deckReviewButton.addActionListener(e -> {
            // 덱 복습을 위한 덱 ID 입력받기
            String deckIdInput = JOptionPane.showInputDialog(this, "복습할 덱 ID를 입력하세요:");
            if (deckIdInput != null && !deckIdInput.isEmpty()) {
                try {
                    int deckId = Integer.parseInt(deckIdInput.trim());
                    // ReviewPanel의 생성자를 호출 (덱 복습: isTodayReview = false)
                    mainFrame.setContentPane(new ReviewPanel(false, deckId));
                    mainFrame.revalidate();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "유효한 숫자를 입력하세요.");
                }
            }
        });

        JPanel reviewPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        reviewPanel.add(reviewTodayButton);
        reviewPanel.add(deckReviewButton);

        // 데이터 관리 화면 버튼
        JButton dataManagementButton = new JButton("데이터 관리");
        dataManagementButton.addActionListener(e -> {
            mainFrame.setContentPane(new DataManagementPanel());
            mainFrame.revalidate();
        });

        // 마이 페이지 버튼
        JButton myPageButton = new JButton("마이 페이지");
        myPageButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "마이 페이지는 아직 구현되지 않았습니다.");
        });

        // 레이아웃 구성
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.add(reviewPanel);
        buttonPanel.add(dataManagementButton);
        buttonPanel.add(myPageButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
}
