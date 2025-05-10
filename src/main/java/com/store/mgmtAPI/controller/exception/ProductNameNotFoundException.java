package com.store.mgmtAPI.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNameNotFoundException extends RuntimeException {
    public ProductNameNotFoundException(String message) {
        super(message);
    }
}
