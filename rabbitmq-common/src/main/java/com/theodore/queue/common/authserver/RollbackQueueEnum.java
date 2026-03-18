package com.theodore.queue.common.authserver;

public enum RollbackQueueEnum {

    // CREDENTIALS
    CREDENTIALS_QUEUE("rollback.credentials.queue"),
    CREDENTIALS_QUEUE_ROUTING_KEY("rollback.credentials.routing-key"),
    CREDENTIALS_DLQ("rollback.credentials.dlq"),
    CREDENTIALS_DLQ_ROUTING_KEY("rollback.credentials.dlq.routing-key"),

    // ROLES
    ROLES_QUEUE("rollback.roles.queue"),
    ROLES_QUEUE_ROUTING_KEY("rollback.roles.routing-key"),
    ROLES_DLQ("rollback.roles.dlq"),
    ROLES_DLQ_ROUTING_KEY("rollback.roles.dlq.routing-key"),

    // SHARED
    QUEUE_EXCHANGE("rollback.exchange"),
    DLX("rollback.dlx"),
    DLQ_OVERFLOW("rollback.dlq.overflow"),
    DLQ_OVERFLOW_EXCHANGE("rollback.dlq.overflow.exchange");

    private final String value;

    RollbackQueueEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
