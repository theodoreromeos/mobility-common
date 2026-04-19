package com.theodore.queue.common.authserver;

public enum RollbackQueueEnum {

    // CREDENTIALS
    CREDENTIALS_QUEUE("rollback.queue.credentials"),
    CREDENTIALS_QUEUE_ROUTING_KEY("rollback.queue.credentials.routing-key"),
    CREDENTIALS_DLQ("rollback.dlq.credentials"),
    CREDENTIALS_DLQ_ROUTING_KEY("rollback.dlq.credentials.routing-key"),

    // ROLES
    ROLES_QUEUE("rollback.queue.roles"),
    ROLES_QUEUE_ROUTING_KEY("rollback.queue.roles.routing-key"),
    ROLES_DLQ("rollback.dlq.roles"),
    ROLES_DLQ_ROUTING_KEY("rollback.dlq.roles.routing-key"),

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
