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

}
