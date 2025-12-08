package com.theodore.infrastructure.common.saga;

/**
 * @param name       The name of the step
 * @param execute    The action that is executed for a step
 * @param compensate The action that is executed when something went wrong in a step
 */
public record SagaStep(String name, SagaAction execute, SagaAction compensate) {
}
