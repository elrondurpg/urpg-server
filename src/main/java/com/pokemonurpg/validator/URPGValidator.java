package com.pokemonurpg.validator;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.Validator;

import java.util.HashMap;

public class URPGValidator implements Validator {
    protected boolean emptyInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }

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
