package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//データベース接続
 
public class DBConnector {
    // データベース接続情報
    private final String JDBC_URL = "jdbc:mysql://localhost/subject_management?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private final String DB_USER = "root";
    private final String DB_PASS = "kcsf";

    // JDBC ドライバのロード
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            System.out.println("JDBC ドライバのロードに成功しました。");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC ドライバが見つかりません: " + e.getMessage());
        }
    }

    //データベース接続状況を取得

    public Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            System.out.println("データベース接続に成功しました。");
            return connection;
        } catch (SQLException e) {
            System.err.println("データベース接続エラーが発生しました。");
            throw e;
        }
    }
}
