package app.view;

import app.controller.LoginManager;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private final LoginManager loginManager = new LoginManager();

    public LoginPanel(JFrame mainFrame) {
        setLayout(new GridLayout(4, 1, 10, 10));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("로그인");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (loginManager.login(username, password)) {
                JOptionPane.showMessageDialog(this, "로그인 성공!");
                mainFrame.setContentPane(new MainPanel(mainFrame)); // 메인 화면으로 전환
                mainFrame.revalidate();
            } else {
                JOptionPane.showMessageDialog(this, "로그인 실패. 사용자 정보를 확인하세요.");
            }
        });

        JButton registerButton = new JButton("회원가입");
        registerButton.addActionListener(e -> {
            mainFrame.setContentPane(new RegisterPanel(mainFrame)); // 회원가입 화면으로 전환
            mainFrame.revalidate();
        });

        add(new JLabel("아이디"));
        add(usernameField);
        add(new JLabel("비밀번호"));
        add(passwordField);
        add(loginButton);
        add(registerButton);
    }
}
