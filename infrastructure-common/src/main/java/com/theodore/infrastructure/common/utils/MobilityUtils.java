package com.theodore.infrastructure.common.utils;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MobilityUtils {

    private MobilityUtils() {
    }

    public static String normalizeEmail(String email) {
        return email == null ? null : email.trim().toLowerCase(Locale.ROOT);
    }

}
