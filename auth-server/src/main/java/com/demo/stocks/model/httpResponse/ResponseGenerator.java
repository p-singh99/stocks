package com.demo.stocks.model.httpResponse;

import com.demo.stocks.utilities.constants.enums.HttpResponseConstants;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;

public class ResponseGenerator {

    private HttpResponseBuilder builder;

    public ResponseGenerator(HttpResponseBuilder builder) {
        this.builder = builder;
    }

    // Create 400 Response
    public HttpResponse createBadRequestResponse(String error, ArrayList<HttpError> subErrors) {
        return this.builder.setTimestamp(new Date())
                .setStatus(HttpStatus.BAD_REQUEST)
                .setError(error)
                .setErrors(subErrors)
                .build();
    }

}
