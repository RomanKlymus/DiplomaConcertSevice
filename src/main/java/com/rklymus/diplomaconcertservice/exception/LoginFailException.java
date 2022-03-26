package com.rklymus.diplomaconcertservice.exception;

public class LoginFailException extends RuntimeException {
    public LoginFailException() {
        super();
    }

    public LoginFailException(String message) {
        super(message);
    }
}
