package com.theodore.infrastructure.common.entities.modeltypes;

import java.util.Optional;

public enum RoleType {

    INTERNAL_SERVICE,
    DRIVER,
    SYS_ADMIN,
    SIMPLE_USER,
    SUBSCRIBER,
    INSURANCE_AGENT,
    MECHANIC,
    MANUFACTURER_REPRESENTATIVE,
    ORGANIZATION_ADMIN;

    public String getScopeValue() {
        return this.name();
    }

    public static Optional<RoleType> getTypeFromString(String value) {
        try {
            return Optional.of(RoleType.valueOf(value));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
