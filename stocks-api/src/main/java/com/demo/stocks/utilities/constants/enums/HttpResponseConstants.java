package com.demo.stocks.utilities.constants.enums;

public enum HttpResponseConstants {

    USER_ALREADY_EXISTS("User with this email already exists"),
    USER_NOT_FOUND("User with this email does not exist. Sign Up!"),
    INCORRECT_USER_DETAILS("Incorrect details entered"),
    SESSION_EXPIRED("User session expired, please login");

    private String response;

    HttpResponseConstants(String response) {
        this.response = response;
    }
    public String getResponse() {
        return this.response;
    }
}
