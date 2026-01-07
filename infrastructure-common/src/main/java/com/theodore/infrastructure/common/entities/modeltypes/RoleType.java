package com.theodore.infrastructure.common.entities.modeltypes;

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

    public static RoleType fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        return RoleType.valueOf(value.trim().toUpperCase());
    }

}
