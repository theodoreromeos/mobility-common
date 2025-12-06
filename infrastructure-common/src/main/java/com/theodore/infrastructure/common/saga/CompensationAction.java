package com.theodore.infrastructure.common.saga;

@FunctionalInterface
public interface CompensationAction {
    void compensate() throws Exception;
}
