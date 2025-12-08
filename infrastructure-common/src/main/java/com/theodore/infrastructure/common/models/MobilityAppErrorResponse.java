package com.theodore.infrastructure.common.models;

import java.time.Instant;

/**
 * When an exception is thrown this response is returned instead of the error in order to have unified and controlled responses.
 */
public record MobilityAppErrorResponse(String message, Instant timestamp) {
}
