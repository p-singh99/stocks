package com.demo.stocks.utilities.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException () {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
