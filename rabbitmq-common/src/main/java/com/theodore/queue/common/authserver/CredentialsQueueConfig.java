package com.theodore.queue.common.authserver;

import org.springframework.amqp.core.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "queue.credentials.enabled", havingValue = "true", matchIfMissing = true)
public class CredentialsQueueConfig {

    @Bean
    public Queue credentialsMainQueue() {
        return QueueBuilder.durable(CredentialsQueueEnum.QUEUE.getValue())
                .quorum()
                .withArgument("x-dead-letter-exchange", CredentialsQueueEnum.DLX.getValue())
                .withArgument("x-dead-letter-routing-key", CredentialsQueueEnum.DLQ_ROUTING_KEY.getValue())
                .build();
    }

    @Bean
    public Queue credentialsDlq() {
        return QueueBuilder.durable(CredentialsQueueEnum.DLQ.getValue())
                .withArgument("x-message-ttl", 86400000) // 24 hours
                .build();
    }

    @Bean
    public Queue credentialsDlqOverflow() {
        return QueueBuilder.durable(CredentialsQueueEnum.DLQ_OVERFLOW.getValue()).build();
    }

    @Bean
    public DirectExchange credentialsMainQueueExchange() {
        return new DirectExchange(CredentialsQueueEnum.QUEUE_EXCHANGE.getValue());
    }

    @Bean
    public DirectExchange credentialsDeadLetterExchange() {
        return new DirectExchange(CredentialsQueueEnum.DLX.getValue());
    }

    @Bean
    public DirectExchange credentialsDlqOverflowExchange() {
        return new DirectExchange(CredentialsQueueEnum.DLQ_OVERFLOW_EXCHANGE.getValue());
    }

    @Bean
    public Binding credentialsMainQueueBinding() {
        return BindingBuilder.bind(credentialsMainQueue()).to(credentialsMainQueueExchange()).with(CredentialsQueueEnum.QUEUE_ROUTING_KEY.getValue());
    }

    @Bean
    public Binding credentialsDlqBinding() {
        return BindingBuilder.bind(credentialsDlq()).to(credentialsDeadLetterExchange()).with(CredentialsQueueEnum.DLQ_ROUTING_KEY.getValue());
    }

    @Bean
    public Binding credentialsDlqOverflowBinding() {
        return BindingBuilder.bind(credentialsDlqOverflow()).to(credentialsDlqOverflowExchange()).with("");
    }

}
