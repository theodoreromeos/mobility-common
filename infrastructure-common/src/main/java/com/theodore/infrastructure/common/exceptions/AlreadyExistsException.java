package com.theodore.infrastructure.common.exceptions;

public class AlreadyExistsException extends RuntimeException {

    private final String resource;

    public AlreadyExistsException(String resource) {
        super(resource);
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

}
