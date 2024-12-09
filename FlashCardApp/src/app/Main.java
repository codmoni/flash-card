package app;

import app.view.LoginPanel;

import javax.swing.*;

public class Main extends JFrame {
    public Main() {
        setTitle("FlashCard App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 초기 화면 설정
        setContentPane(new LoginPanel(this));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main mainFrame = new Main();
            mainFrame.setVisible(true);
        });
    }
}
