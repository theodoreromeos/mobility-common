package com.theodore.infrastructure.common.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StrongPasswordValidatorImpl.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPasswordValidator {

    String message() default "Password must be at least 8 characters long and include upper, lower case letters, digits and special characters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
