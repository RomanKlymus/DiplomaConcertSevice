package com.rklymus.diplomaconcertservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class LoginFailException extends RuntimeException {
    public LoginFailException() {
        super();
    }

    public LoginFailException(String message) {
        super(message);
    }
}
