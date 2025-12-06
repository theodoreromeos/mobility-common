package com.theodore.infrastructure.common.exceptions;

public class SagaOrchestrationException extends RuntimeException {

    public SagaOrchestrationException(final String message, Throwable cause) {
        super("Saga failed: " + message, cause);
    }

}
