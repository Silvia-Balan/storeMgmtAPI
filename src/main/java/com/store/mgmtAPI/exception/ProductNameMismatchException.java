package com.store.mgmtAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProductNameMismatchException extends RuntimeException {
    public ProductNameMismatchException(String message) {
        super(message);
    }
}
