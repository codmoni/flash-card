package app.service;

import app.dao.UserDao;

import java.sql.SQLException;

public class UserService {
    private final UserDao userDao = new UserDao();

    // 회원가입
    public boolean registerUser(String username, String password, String email) {
        try {
            userDao.addUser(username, password, email);
            return true;
        } catch (SQLException e) {
            System.err.println("회원가입 오류: " + e.getMessage());
            return false;
        }
    }

    // 로그인
    public boolean login(String username, String password) {
        try {
            return userDao.validateUser(username, password);
        } catch (SQLException e) {
            System.err.println("로그인 오류: " + e.getMessage());
            return false;
        }
    }
}
