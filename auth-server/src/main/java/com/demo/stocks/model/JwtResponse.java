package com.demo.stocks.model;

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private int userId;
    private String email;

    public JwtResponse(String accessToken, String refreshToken, int id, String email) {
        this.token = accessToken;
        this.refreshToken = refreshToken;
        this.userId = id;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
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
