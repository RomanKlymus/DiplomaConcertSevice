package com.rklymus.diplomaconcertservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenOperationException extends RuntimeException {
    private static final String MESSAGE = "This action is forbidden for you";
    public ForbiddenOperationException() {
        super(MESSAGE);
    }
}
