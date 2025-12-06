package com.theodore.infrastructure.common.saga;

import com.theodore.infrastructure.common.exceptions.SagaOrchestrationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SagaOrchestrator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SagaOrchestrator.class);

    private final List<SagaStepWrapper> steps = new ArrayList<>();

    public SagaOrchestrator step(SagaStep step, CompensationAction compensation) {
        steps.add(new SagaStepWrapper(step, compensation));
        return this;
    }

    public void run() {
        List<SagaStepWrapper> executed = new ArrayList<>();
        try {
            for (SagaStepWrapper step : steps) {
                step.execute();
                executed.add(step);
            }
        } catch (Exception e) {
            // Run compensation in reverse
            Collections.reverse(executed);
            for (SagaStepWrapper step : executed) {
                try {
                    LOGGER.info("Compensating step: {}", step);
                    step.compensate();
                } catch (Exception ce) {
                    LOGGER.error("Compensation failed: {}", ce.getMessage());
                }
            }
            throw new SagaOrchestrationException(e.getMessage(), e);
        }
    }
}
