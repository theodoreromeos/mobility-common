package com.theodore.queue.common.services;

import com.theodore.queue.common.authserver.CredentialsRollbackEventDto;
import com.theodore.queue.common.authserver.RolesRollbackEventDto;
import com.theodore.queue.common.emails.EmailDto;

public interface MessagingService {

    void sendToEmailService(EmailDto dto);

    /**
     * Sends a rollback message for the credentials of a user to the auth server via a queue.
     */
    void rollbackCredentialsSave(CredentialsRollbackEventDto dto);

    /**
     * Sends a rollback message for the assignment of the roles to a user to the auth server via a queue.
     */
    void rollbackRolesAssignment(RolesRollbackEventDto dto);

}
