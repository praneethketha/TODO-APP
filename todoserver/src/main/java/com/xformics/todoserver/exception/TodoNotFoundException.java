package com.xformics.todoserver.exception;

// defining custom not found exception
public class TodoNotFoundException extends Exception{
    public TodoNotFoundException() {
        super();
    }

    public TodoNotFoundException(String message) {
        super(message);
    }

    public TodoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TodoNotFoundException(Throwable cause) {
        super(cause);
    }

    protected TodoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

