package app.view;

import app.controller.LoginManager;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    private final LoginManager loginManager = new LoginManager();

    public RegisterPanel(JFrame mainFrame) {
        setLayout(new GridLayout(5, 1, 10, 10));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField emailField = new JTextField();

        JButton registerButton = new JButton("회원가입");
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();

            if (loginManager.register(username, password, email)) {
                JOptionPane.showMessageDialog(this, "회원가입 성공!");
                mainFrame.setContentPane(new LoginPanel(mainFrame)); // 로그인 화면으로 전환
                mainFrame.revalidate();
            } else {
                JOptionPane.showMessageDialog(this, "회원가입 실패. 다시 시도해주세요.");
            }
        });

        JButton backButton = new JButton("뒤로");
        backButton.addActionListener(e -> {
            mainFrame.setContentPane(new LoginPanel(mainFrame)); // 로그인 화면으로 돌아가기
            mainFrame.revalidate();
        });

        add(new JLabel("아이디"));
        add(usernameField);
        add(new JLabel("비밀번호"));
        add(passwordField);
        add(new JLabel("이메일"));
        add(emailField);
        add(registerButton);
        add(backButton);
    }
}
