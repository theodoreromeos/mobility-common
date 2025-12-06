package com.theodore.infrastructure.common.exceptions;

public class RollbackProcessingException extends RuntimeException {

    public RollbackProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

}
