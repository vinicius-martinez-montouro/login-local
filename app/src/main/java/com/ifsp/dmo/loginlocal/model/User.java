package com.ifsp.dmo.loginlocal.model;

import androidx.annotation.NonNull;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @NonNull
    @Override
    public String toString() {
        return getUsername() + " | " + getPassword();
    }
}
