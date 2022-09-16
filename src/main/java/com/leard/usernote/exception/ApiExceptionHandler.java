package com.leard.usernote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    private final String STATUS_ERROR = "ng";
    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleApiBadRequest(BadRequestException e){
        ResponseError responseError = new ResponseError(STATUS_ERROR, e.getMessage());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(responseError, httpStatus);
    }
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleApiNotFound(NotFoundException e){
        ResponseError responseError = new ResponseError(STATUS_ERROR, e.getMessage());
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(responseError, httpStatus);
    }
}