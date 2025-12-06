package com.theodore.infrastructure.common.models;

import java.time.Instant;

public record MobilityAppErrorResponse(String message, Instant timestamp) {
}
