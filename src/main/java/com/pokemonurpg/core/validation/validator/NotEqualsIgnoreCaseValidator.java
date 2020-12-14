package com.pokemonurpg.core.validation;

public class NotEqualsIgnoreCaseValidator implements UrpgValidator {
    private String[] invalidInputs;

    public NotEqualsIgnoreCaseValidator(String... invalidInputs) {
        this.invalidInputs = invalidInputs;
    }

    public boolean isValid(Object input) {
        if (input instanceof String) {
            for (String s : invalidInputs) {
                if (((String) input).equalsIgnoreCase(s))
                    return false;
            }
            return true;
        }
        return false;
    }
}
