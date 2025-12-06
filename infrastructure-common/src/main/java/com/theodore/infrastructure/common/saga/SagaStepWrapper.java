package com.theodore.infrastructure.common.saga;

public class SagaStepWrapper {
    private final SagaStep step;
    private final CompensationAction compensation;

    public SagaStepWrapper(SagaStep step, CompensationAction compensation) {
        this.step = step;
        this.compensation = compensation;
    }

    public void execute() throws Exception {
        step.execute();
    }

    public void compensate() throws Exception {
        compensation.compensate();
    }
}
