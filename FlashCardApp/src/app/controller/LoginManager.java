package app.controller;

import app.service.UserService;

public class LoginManager {
    private final UserService userService = new UserService();

    // 로그인 요청 처리
    public boolean login(String username, String password) {
        return userService.login(username, password);
    }

    // 회원가입 요청 처리
    public boolean register(String username, String password, String email) {
        return userService.registerUser(username, password, email);
    }
}
