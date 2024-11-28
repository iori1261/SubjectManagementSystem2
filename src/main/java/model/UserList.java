package model;

import java.util.ArrayList;
import java.util.List;


public class UserList {
    private List<UserData> users;


    public UserList() {
        this.users = new ArrayList<>();
    }

    // 全てのユーザーを取得

    public List<UserData> getUsers() {
        return users;
    }

    //ユーザーを追加

    public void addUser(UserData user) {
        users.add(user);
    }

    //ユーザー名でユーザーを検索し取得

    public UserData getUserByUsername(String username) {
        for (UserData user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
