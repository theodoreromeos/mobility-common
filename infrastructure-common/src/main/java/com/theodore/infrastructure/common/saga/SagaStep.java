package com.theodore.infrastructure.common.saga;

@FunctionalInterface
public interface SagaStep {
    void execute() throws Exception;
}
