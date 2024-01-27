package com.demo.stocks.model.httpResponse;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;

public class HttpResponseBuilder {

    private Date timestamp;

    private HttpStatus status;

    private String error;

    private ArrayList<HttpError> errors;

    public HttpResponseBuilder() {}

    public HttpResponseBuilder setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public HttpResponseBuilder setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public HttpResponseBuilder setError(String error) {
        this.error = error;
        return this;
    }

    public void addError(HttpError error) {
        this.errors.add(error);
    }

    public HttpResponseBuilder setErrors(ArrayList<HttpError> errors) {
        this.errors = errors;
        return this;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public ArrayList<HttpError> getErrors() {
        return errors;
    }

    public HttpResponse build() {
        return new HttpResponse(this);
    }
}
