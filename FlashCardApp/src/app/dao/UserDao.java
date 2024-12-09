package app.dao;

import java.sql.*;

public class UserDao {
    // 1. 사용자 추가
    public void addUser(String username, String password, String email) throws SQLException {
        String query = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.executeUpdate();
            System.out.println("사용자가 성공적으로 추가되었습니다!");
        }
    }

    // 2. 사용자 조회 (ID로 조회)
    public String getUserById(int userId) throws SQLException {
        String query = "SELECT * FROM Users WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return "ID: " + rs.getInt("user_id") +
                           ", Username: " + rs.getString("username") +
                           ", Email: " + rs.getString("email");
                }
            }
        }
        return "사용자를 찾을 수 없습니다.";
    }

    // 3. 사용자 로그인 확인
    public boolean validateUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // 결과가 있으면 로그인 성공
            }
        }
    }
}
