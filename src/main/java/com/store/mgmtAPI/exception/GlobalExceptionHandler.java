package com.store.mgmtAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNameAlreadyExistsException.class)
    public ResponseEntity<DetailedErrorResponse> handleNameAlreadyExists(ProductNameAlreadyExistsException exception, WebRequest webRequest){
        DetailedErrorResponse detailedErrorResponse = new DetailedErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );

        return new ResponseEntity<>(detailedErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNameNotFoundException.class)
    public ResponseEntity<DetailedErrorResponse> handleNameNotFound(ProductNameNotFoundException exception, WebRequest webRequest){
        DetailedErrorResponse detailedErrorResponse = new DetailedErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );

        return new ResponseEntity<>(detailedErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNameMismatchException.class)
    public ResponseEntity<DetailedErrorResponse> handleNameMismatchExists(ProductNameMismatchException exception, WebRequest webRequest){
        DetailedErrorResponse detailedErrorResponse = new DetailedErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );

        return new ResponseEntity<>(detailedErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
