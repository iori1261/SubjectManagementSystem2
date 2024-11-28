package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.UserData;


public class UserDAO {
    private final DBConnector dbConnector = new DBConnector();

    //ユーザー認証
    public UserData validateUser(String username, String password) {
        String sql = "SELECT * FROM sUser WHERE username = ? AND password = ?";
        UserData user = null;

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new UserData(rs.getString("username"), rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            System.err.println("validateUser エラー: " + e.getMessage());
        }

        return user;
    }

}
