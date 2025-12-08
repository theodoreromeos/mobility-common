package com.theodore.infrastructure.common.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class StrongPasswordValidatorImpl implements ConstraintValidator<StrongPasswordValidator, String> {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=\\S+$)"          +  // no whitespace anywhere
                    "(?=.*[a-z])"         +  // at least one lowercase
                    "(?=.*[A-Z])"         +  // at least one uppercase
                    "(?=.*\\d)"           +  // at least one digit
                    "(?=.*[^A-Za-z0-9])"  +  // at least one non-alphanumeric
                    ".{8,}$"                 // at least 8 chars
    );

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

}
