package com.codegym.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PhoneExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PhoneExistsException(String message) {
        super(message);
    }
}