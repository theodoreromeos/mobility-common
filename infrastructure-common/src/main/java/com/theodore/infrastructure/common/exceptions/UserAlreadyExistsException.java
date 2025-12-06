package com.theodore.infrastructure.common.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    private final String email;

    public UserAlreadyExistsException(String email) {
        super(email);
        this.email = email;
    }

    public String getEmail() { return email; }

}
