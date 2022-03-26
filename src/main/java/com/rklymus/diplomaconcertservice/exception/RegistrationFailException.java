package com.rklymus.diplomaconcertservice.exception;

public class RegistrationFailException extends RuntimeException {
    public RegistrationFailException() {
        super();
    }

    public RegistrationFailException(String message) {
        super(message);
    }
}
