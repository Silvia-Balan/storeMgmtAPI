package com.store.mgmtAPI.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProductNameAlreadyExistsException extends RuntimeException {

    public ProductNameAlreadyExistsException(String message) {
        super(message);
    }
}
