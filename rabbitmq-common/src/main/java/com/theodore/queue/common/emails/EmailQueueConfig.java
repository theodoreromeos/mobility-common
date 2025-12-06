package com.theodore.queue.common.emails;

import org.springframework.amqp.core.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnProperty(name = "queue.email.enabled", havingValue = "true", matchIfMissing = true)
public class EmailQueueConfig {

    @Bean
    public Queue emailMainQueue() {
        return QueueBuilder.durable(EmailQueueEnum.QUEUE.getValue())
                .withArgument("x-dead-letter-exchange", EmailQueueEnum.DLX.getValue())
                .withArgument("x-dead-letter-routing-key", EmailQueueEnum.DLQ_ROUTING_KEY.getValue())
                .build();
    }

    @Bean
    public Queue emailDlq() {
        return QueueBuilder.durable(EmailQueueEnum.DLQ.getValue())
                .withArgument("x-message-ttl", 86400000) // 24 hours
                .build();
    }

    @Bean
    public DirectExchange emailMainQueueExchange() {
        return new DirectExchange(EmailQueueEnum.QUEUE_EXCHANGE.getValue());
    }

    @Bean
    public DirectExchange emailDeadLetterExchange() {
        return new DirectExchange(EmailQueueEnum.DLX.getValue());
    }

    @Bean
    public Binding emailMainQueueBinding() {
        return BindingBuilder.bind(emailMainQueue()).to(emailMainQueueExchange()).with(EmailQueueEnum.QUEUE_ROUTING_KEY.getValue());
    }

    @Bean
    public Binding emailDlqBinding() {
        return BindingBuilder.bind(emailDlq()).to(emailDeadLetterExchange()).with(EmailQueueEnum.DLQ_ROUTING_KEY.getValue());
    }

}
