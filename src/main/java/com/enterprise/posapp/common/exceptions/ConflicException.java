package com.enterprise.posapp.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflicException extends RuntimeException {
    public ConflicException(String message) {
        super(message);
    }
}
