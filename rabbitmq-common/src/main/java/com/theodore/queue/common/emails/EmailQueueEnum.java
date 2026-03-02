package com.theodore.queue.common.emails;

public enum EmailQueueEnum {

    QUEUE("email.queue"),
    QUEUE_EXCHANGE("email.exchange"),
    QUEUE_ROUTING_KEY("email.routing.key"),

    DLQ("email.dlq"),
    DLX("email.dlx"),
    DLQ_ROUTING_KEY("email.routing.key.dlx"),

    DLQ_OVERFLOW("email.dlq.overflow"),
    DLQ_OVERFLOW_EXCHANGE("email.dlq.overflow.exchange");

    private final String value;

    EmailQueueEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
