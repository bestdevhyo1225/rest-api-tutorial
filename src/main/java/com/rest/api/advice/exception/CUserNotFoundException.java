package com.rest.api.advice.exception;

public class CUesrNotFoundException extends RuntimeException {
    public CUesrNotFoundException(String message, Throwable t) {
        super(message, t);
    }

    public CUesrNotFoundException(String message) {
        super(message);
    }

    public CUesrNotFoundException() {
        super();
    }
}
