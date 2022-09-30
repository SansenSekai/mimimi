package com.test.projects.mimimi.exception;

public class CheaterException extends RuntimeException {
    public CheaterException() {
        super("");
    }

    public CheaterException(String msg) {
        super(msg);
    }

    public CheaterException(String msg, Throwable t) {
        super(msg, t);
    }
}
