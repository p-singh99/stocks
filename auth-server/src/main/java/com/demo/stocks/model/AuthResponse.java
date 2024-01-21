package com.demo.stocks.model;

public class AuthResponse {

    private int userId;
    private String email;

    public AuthResponse(int id, String email) {
        this.userId = id;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
