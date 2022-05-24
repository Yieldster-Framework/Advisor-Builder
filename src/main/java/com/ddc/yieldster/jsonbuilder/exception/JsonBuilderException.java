package com.ddc.yieldster.jsonbuilder.exception;

public class JsonBuilderException extends Exception {

    public JsonBuilderException() {
    }

    public JsonBuilderException(String message) {
        super(message);
    }

    public JsonBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonBuilderException(Throwable cause) {
        super(cause);
    }

    public JsonBuilderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

