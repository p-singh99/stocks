package com.demo.stocks.controller.error;

import com.demo.stocks.utilities.constants.ErrorMessage;
import com.demo.stocks.utilities.exceptions.UserAlreadyExistsException;
import com.demo.stocks.utilities.exceptions.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerControllerAdvise {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleUserNotFound (final Exception exception, final HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(exception.getMessage());
        response.setErrorCode(HttpStatus.NOT_FOUND);
        response.setTime(new java.util.Date());
        return response;
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public @ResponseBody ErrorResponse handleUserExists (final Exception exception, final HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(exception.getMessage());
        response.setErrorCode(HttpStatus.CONFLICT);
        response.setTime(new java.util.Date());
        return response;
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public @ResponseBody ErrorResponse handleSaveException (final Exception exception, final HttpServletRequest request) {
//        ErrorResponse response = new ErrorResponse();
//        response.setMessage(ErrorMessage.ERROR_SAVING_USER);
//        response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
//        response.setTime(new java.util.Date());
//        return response;
//    }
}
