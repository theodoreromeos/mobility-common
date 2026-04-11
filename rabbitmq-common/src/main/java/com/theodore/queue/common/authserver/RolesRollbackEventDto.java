package com.theodore.queue.common.authserver;

import com.theodore.infrastructure.common.entities.enums.RoleType;

import java.util.Set;

public record RolesRollbackEventDto(String userId, Set<RoleType> rolesToBeRemoved) {
}
