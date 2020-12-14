package com.pokemonurpg.core.validation;

import com.pokemonurpg.core.validation.annotation.GreaterThan;
import com.pokemonurpg.core.validation.annotation.LengthBetween;
import com.pokemonurpg.core.validation.service.ObjectValidationService;
import com.pokemonurpg.core.validation.validator.UrpgValidator;
import com.pokemonurpg.core.validation.validator.UrpgValidatorFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ObjectValidationServiceTest {/*
    private static final int MIN_VALUE = 5;
    private static final int MAX_VALUE = 10;

    @InjectMocks
    private ObjectValidationService objectValidationService = new ObjectValidationService();

    @Mock
    private UrpgValidatorFactory urpgValidatorFactory;

    @Mock
    private UrpgValidator urpgValidator;

    @Before
    public void init() {
        when(urpgValidatorFactory.getValidator(Matchers.any())).thenReturn(urpgValidator);
    }

    @Test
    public void validateReturnsFalseWhenInvalid() throws IllegalAccessException {
        when(urpgValidator.isValid(Matchers.any())).thenReturn(false);

        TestObject obj = new TestObject("abc", 3);
        assertFalse(objectValidationService.validate(obj));
    }

    @Test
    public void validateReturnsFalseWhenObjectNull() throws IllegalAccessException {
        assertFalse(objectValidationService.validate(null));
    }

    @Test
    public void validateReturnsFalseWhenPropertyNull() throws IllegalAccessException {
        TestObject obj = new TestObject(null, null);
        assertFalse(objectValidationService.validate(null));
    }

    @Test
    public void validateReturnsTrueWhenValid() throws IllegalAccessException {
        when(urpgValidator.isValid(Matchers.any())).thenReturn(true);
        TestObject obj = new TestObject("abcdef", 7);
        assertTrue(objectValidationService.validate(obj));
    }

    @Test
    public void validateReturnsTrueWhenFieldNullable() {

    }

    @Test
    public void validateNullableReturnsFalseWhenInvalid() throws IllegalAccessException {
        when(urpgValidator.isValid(Matchers.any())).thenReturn(false);
        TestObject obj = new TestObject("abc", 3);
        assertFalse(objectValidationService.validateNullable(obj));
    }

    @Test
    public void validateNullableReturnsFalseWhenObjectNull() throws IllegalAccessException {
        assertFalse(objectValidationService.validateNullable(null));
    }

    @Test
    public void validateNullableReturnsTrueWhenPropertyNull() throws IllegalAccessException {
        TestObject obj = new TestObject(null, null);
        assertTrue(objectValidationService.validateNullable(obj));
    }

    @Test
    public void validateNullableReturnsTrueWhenValid() throws IllegalAccessException {
        when(urpgValidator.isValid(Matchers.any())).thenReturn(true);
        TestObject obj = new TestObject("abcdef", 7);
        assertTrue(objectValidationService.validateNullable(obj));
    }

    @Test
    public void validateNullableReturnsTrueWhenFieldNullable() {

    }

    public static class TestObject {
        @LengthBetween(min = MIN_VALUE, max = MAX_VALUE)
        private String testString;

        @GreaterThan(min = MIN_VALUE)
        private Integer testInt;

        public TestObject(String testString, Integer testInt) {
            this.testString = testString;
            this.testInt = testInt;
        }
    }*/
}