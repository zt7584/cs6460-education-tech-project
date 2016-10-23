package edu.dev.entity;

/**
 * Created by tengzhao on 9/18/16.
 */
public class User {
    public static String TAG = User.class.getCanonicalName();

    private String username;
    private String name;
    private String password;

    public User() {
    }

    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "[User]: {"
                + "username: " + username + ","
                + "name: " + name + ","
                + "password: " + password + "}";
    }
}
