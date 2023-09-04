package com.intuit.interview.businessprofileservice.exceptions;

public class AppException extends RuntimeException {

    protected AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
