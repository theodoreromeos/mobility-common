package com.theodore.queue.common.authserver;

import org.springframework.amqp.core.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "queue.rollback.enabled", havingValue = "true", matchIfMissing = true)
public class RollbackQueueConfig {

    @Bean
    public DirectExchange rollbackExchange() {
        return new DirectExchange(RollbackQueueEnum.QUEUE_EXCHANGE.getValue());
    }

    @Bean
    public DirectExchange rollbackDeadLetterExchange() {
        return new DirectExchange(RollbackQueueEnum.DLX.getValue());
    }

    @Bean
    public DirectExchange rollbackDlqOverflowExchange() {
        return new DirectExchange(RollbackQueueEnum.DLQ_OVERFLOW_EXCHANGE.getValue());
    }

    @Bean
    public Queue rollbackDlqOverflow() {
        return QueueBuilder.durable(RollbackQueueEnum.DLQ_OVERFLOW.getValue()).build();
    }

    @Bean
    public Binding rollbackDlqOverflowBinding() {
        return BindingBuilder
                .bind(rollbackDlqOverflow())
                .to(rollbackDlqOverflowExchange())
                .with("");
    }

    // CREDENTIALS QUEUE

    @Bean
    public Queue credentialsRollbackQueue() {
        return QueueBuilder.durable(RollbackQueueEnum.CREDENTIALS_QUEUE.getValue())
                .quorum()
                .withArgument("x-dead-letter-exchange", RollbackQueueEnum.DLX.getValue())
                .withArgument("x-dead-letter-routing-key", RollbackQueueEnum.CREDENTIALS_DLQ_ROUTING_KEY.getValue())
                .build();
    }

    @Bean
    public Binding credentialsRollbackBinding() {
        return BindingBuilder
                .bind(credentialsRollbackQueue())
                .to(rollbackExchange())
                .with(RollbackQueueEnum.CREDENTIALS_QUEUE_ROUTING_KEY.getValue());
    }

    @Bean
    public Queue credentialsDlq() {
        return QueueBuilder.durable(RollbackQueueEnum.CREDENTIALS_DLQ.getValue())
                .withArgument("x-message-ttl", 86400000) // 24 hours
                .deadLetterExchange(RollbackQueueEnum.DLQ_OVERFLOW_EXCHANGE.getValue())
                .deadLetterRoutingKey("")
                .build();
    }

    @Bean
    public Binding rollbackDlqBinding() {
        return BindingBuilder
                .bind(credentialsDlq())
                .to(rollbackDeadLetterExchange())
                .with(RollbackQueueEnum.CREDENTIALS_DLQ_ROUTING_KEY.getValue());
    }

    // ROLES QUEUE

    @Bean
    public Queue rolesRollbackQueue() {
        return QueueBuilder.durable(RollbackQueueEnum.ROLES_QUEUE.getValue())
                .quorum()
                .withArgument("x-dead-letter-exchange", RollbackQueueEnum.DLX.getValue())
                .withArgument("x-dead-letter-routing-key", RollbackQueueEnum.ROLES_DLQ_ROUTING_KEY.getValue())
                .build();
    }

    @Bean
    public Binding rolesRollbackBinding() {
        return BindingBuilder
                .bind(rolesRollbackQueue())
                .to(rollbackExchange())
                .with(RollbackQueueEnum.ROLES_QUEUE_ROUTING_KEY.getValue());
    }

    @Bean
    public Queue rolesDlq() {
        return QueueBuilder.durable(RollbackQueueEnum.ROLES_DLQ.getValue())
                .withArgument("x-message-ttl", 86400000)
                .deadLetterExchange(RollbackQueueEnum.DLQ_OVERFLOW_EXCHANGE.getValue())
                .deadLetterRoutingKey("")
                .build();
    }

    @Bean
    public Binding rolesDlqBinding() {
        return BindingBuilder.bind(rolesDlq())
                .to(rollbackDeadLetterExchange())
                .with(RollbackQueueEnum.ROLES_DLQ_ROUTING_KEY.getValue());
    }

}
