package com.rklymus.diplomaconcertservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class RegistrationFailException extends RuntimeException {
    public RegistrationFailException() {
        super();
    }

    public RegistrationFailException(String message) {
        super(message);
    }
}
