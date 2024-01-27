package com.demo.stocks.utilities.exceptions;

public class RefreshTokenExpiredException extends Exception {

    public RefreshTokenExpiredException() {
        super();
    }

    public RefreshTokenExpiredException(String message) {
        super(message);
    }

}
