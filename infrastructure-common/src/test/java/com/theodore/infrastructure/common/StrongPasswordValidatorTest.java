package com.theodore.infrastructure.common;

import com.theodore.infrastructure.common.utils.StrongPasswordValidatorImpl;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StrongPasswordValidatorTest {

    private StrongPasswordValidatorImpl validator;

    @BeforeEach
    void setUp() {
        validator = new StrongPasswordValidatorImpl();
    }

    // A dummy context, since your implementation doesn't actually use it
    private final ConstraintValidatorContext dummyContext = null;

    @ParameterizedTest
    @ValueSource(strings = {
            "Password1!",
            "Abcdefg1@",
            "Xx9$xxxx"
    })
    @DisplayName("Valid passwords should return true")
    void whenPasswordMeetsAllCriteria_thenIsValidReturnsTrue(String password) {
        assertTrue(validator.isValid(password, dummyContext),
                () -> "Expected valid for \"" + password + "\"");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "password1!",    // missing uppercase
            "PASSWORD1!",    // missing lowercase
            "Password!",     // missing digit
            "Password1",     // missing special character
            "Pass word1!",   // contains whitespace
            "P1!"            // too short
    })
    @DisplayName("Invalid passwords should return false")
    void whenPasswordFailsAnyCriterion_thenIsValidReturnsFalse(String password) {
        assertFalse(validator.isValid(password, dummyContext),
                () -> "Expected invalid for \"" + password + "\"");
    }

    @Test
    @DisplayName("Null password should return false")
    void whenPasswordIsNull_thenIsValidReturnsFalse() {
        assertFalse(validator.isValid(null, dummyContext));
    }

}
