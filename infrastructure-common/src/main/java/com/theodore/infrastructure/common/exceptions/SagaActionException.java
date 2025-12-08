package com.theodore.infrastructure.common.exceptions;

public class SagaActionException extends Exception {

    public enum Phase {
        EXECUTION,
        COMPENSATION
    }

    private final String stepName;
    private final Phase phase;

    public SagaActionException(String stepName, Phase phase, Throwable cause) {
        super("Saga " + phase.name().toLowerCase() + " failed at step '" + stepName + "'", cause);
        this.stepName = stepName;
        this.phase = phase;
    }

    public String getStepName() {
        return stepName;
    }

    public Phase getPhase() {
        return phase;
    }
}
