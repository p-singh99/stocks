package com.demo.stocks.model.httpResponse;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;

public class HttpResponse {

    private Date timestamp;

    private HttpStatus status;

    private String error;

    private ArrayList<HttpError> errors;

    public HttpResponse(Date timestamp, HttpStatus status, String error, ArrayList <HttpError> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.errors = errors;
    }

    public HttpResponse(HttpResponseBuilder builder) {
        this.timestamp = builder.getTimestamp();
        this.status = builder.getStatus();
        this.error = builder.getError();
        this.errors = builder.getErrors();
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ArrayList<HttpError> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<HttpError> errors) {
        this.errors = errors;
    }
}
