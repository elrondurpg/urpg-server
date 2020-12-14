package com.pokemonurpg.core.validation.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LengthBetweenValidatorTest {
    private static final int MIN_VALUE = 5;
    private static final int MAX_VALUE = 10;

    private LengthBetweenValidator lengthBetweenValidator = new LengthBetweenValidator(MIN_VALUE, MAX_VALUE);

    @Test
    public void returnsFalseWhenNotAString() {
        assertFalse(lengthBetweenValidator.isValid(46));
    }

    @Test
    public void returnsFalseWhenNull() {
        assertFalse(lengthBetweenValidator.isValid(null));
    }

    @Test
    public void returnsFalseWhenGreaterThanMax() {
        assertFalse(lengthBetweenValidator.isValid("abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void returnsFalseWhenLessThanMin() {
        assertFalse(lengthBetweenValidator.isValid("abc"));
    }

    @Test
    public void returnsTrueWhenBetweenMaxAndMin() {
        assertTrue(lengthBetweenValidator.isValid("abcdef"));
    }

}
