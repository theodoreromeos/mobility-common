package com.theodore.infrastructure.common.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Locale;

@Component
public class MobilityUtils {

    private MobilityUtils() {
    }

    public static String normalizeEmail(String email) {
        return email == null ? null : email.trim().toLowerCase(Locale.ROOT);
    }

    public static String getExceptionMessage(BindingResult bindingResult) {
        return bindingResult
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList()
                .stream().findFirst().orElse("Bad Request");
    }

}
