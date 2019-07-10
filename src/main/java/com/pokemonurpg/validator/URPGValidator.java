package com.pokemonurpg.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class URPGValidator implements Validator {
    protected boolean emptyInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }

    protected boolean isStringLengthBetween(String input, int min, int max) { return !emptyInputString(input) && isIntegerBetween(input.length(), min, max); }

    protected boolean isIntegerBetween(int input, int min, int max) {
        return input >= min && input <= max;
    }

    protected boolean isDoubleBetween(double input, double min, double max) {
        return input >= min && input <= max;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
