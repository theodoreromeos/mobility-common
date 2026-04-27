package com.theodore.queue.common.services;

import com.theodore.queue.common.authserver.RolesRollbackEventDto;
import com.theodore.queue.common.authserver.RollbackQueueEnum;
import com.theodore.queue.common.authserver.CredentialsRollbackEventDto;
import com.theodore.queue.common.emails.EmailDto;
import com.theodore.queue.common.emails.EmailQueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.UUID;

public class MessagingServiceImpl implements MessagingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessagingServiceImpl.class);

    private final RabbitTemplate rabbitTemplate;

    public MessagingServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendToEmailService(EmailDto emails) {
        LOGGER.info("Sending emails");

        rabbitTemplate.convertAndSend(
                EmailQueueEnum.QUEUE_EXCHANGE.getValue(),
                EmailQueueEnum.QUEUE_ROUTING_KEY.getValue(),
                emails,
                message -> {
                    message.getMessageProperties()
                            .setMessageId(UUID.randomUUID().toString());
                    return message;
                }
        );
    }

    @Override
    public void rollbackCredentialsSave(CredentialsRollbackEventDto dto) {
        rabbitTemplate.convertAndSend(
                RollbackQueueEnum.QUEUE_EXCHANGE.getValue(),
                RollbackQueueEnum.CREDENTIALS_QUEUE_ROUTING_KEY.getValue(),
                dto
        );
    }

    @Override
    public void rollbackRolesAssignment(RolesRollbackEventDto dto) {
        rabbitTemplate.convertAndSend(
                RollbackQueueEnum.QUEUE_EXCHANGE.getValue(),
                RollbackQueueEnum.ROLES_QUEUE_ROUTING_KEY.getValue(),
                dto
        );
    }

}