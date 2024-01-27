package com.demo.stocks.controller.error;

import org.springframework.http.HttpStatus;

import java.util.Date;
public class ErrorResponse {

    private String message;
    private HttpStatus errorCode;
    private Date time;
    public ErrorResponse() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
