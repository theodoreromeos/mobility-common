package com.theodore.infrastructure.common.saga;

import com.theodore.infrastructure.common.exceptions.SagaActionException;

@FunctionalInterface
public interface SagaAction {
    void perform() throws SagaActionException;
}

