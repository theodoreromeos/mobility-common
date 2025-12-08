package com.theodore.infrastructure.common.saga;

import com.theodore.infrastructure.common.exceptions.SagaActionException;
import com.theodore.infrastructure.common.exceptions.SagaOrchestrationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SagaOrchestrator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SagaOrchestrator.class);

    private final List<SagaStep> steps = new ArrayList<>();

    public SagaOrchestrator step(String name, SagaAction execute, SagaAction compensate) {
        steps.add(new SagaStep(name, execute, compensate));
        return this;
    }

    public void run() {
        List<SagaStep> executed = new ArrayList<>();
        try {
            for (SagaStep step : steps) {
                step.execute().perform();
                executed.add(step);
            }
        } catch (Exception e) {
            // Run compensation in reverse
            Collections.reverse(executed);
            for (SagaStep step : executed) {
                try {
                    LOGGER.info("Compensating step: {}", step);
                    step.compensate().perform();
                } catch (Exception ce) {
                    var compensationEx = new SagaActionException(step.name(), SagaActionException.Phase.COMPENSATION, e);
                    LOGGER.error("Compensation failed", compensationEx);
                }
            }
            throw new SagaOrchestrationException(e.getMessage(), e);
        }
    }
}
