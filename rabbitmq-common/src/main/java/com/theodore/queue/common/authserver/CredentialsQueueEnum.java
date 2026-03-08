package com.theodore.queue.common.authserver;

public enum CredentialsQueueEnum {

    QUEUE("credentials.queue"),
    QUEUE_EXCHANGE("credentials.exchange"),
    QUEUE_ROUTING_KEY("credentials.routing.key"),

    DLQ("credentials.dlq"),
    DLX("credentials.dlx"),
    DLQ_ROUTING_KEY("credentials.routing.key.dlx"),

    DLQ_OVERFLOW("credentials.dlq.overflow"),
    DLQ_OVERFLOW_EXCHANGE("credentials.dlq.overflow.exchange");

    private final String value;

    CredentialsQueueEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
