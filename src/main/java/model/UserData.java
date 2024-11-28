package model;


public class UserData {
    private String username;
    private String password;


    public UserData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // ユーザー名の取得
    public String getUsername() {
        return username;
    }

    // パスワードの取得
    public String getPassword() {
        return password;
    }

    // ユーザー名の設定
    public void setUsername(String username) {
        this.username = username;
    }

    // パスワードの設定
    public void setPassword(String password) {
        this.password = password;
    }
}
