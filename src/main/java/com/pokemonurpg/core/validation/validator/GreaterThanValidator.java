package com.pokemonurpg.core.validation;

public class GreaterThanValidator implements UrpgValidator {
    private int min;
    public GreaterThanValidator (int min) {
        this.min = min;
    }

    @Override
    public boolean isValid(Object input) {
        if (input instanceof Integer) {
            Integer i = (Integer) input;
            return i > min;
        }
        return false;
    }
}
