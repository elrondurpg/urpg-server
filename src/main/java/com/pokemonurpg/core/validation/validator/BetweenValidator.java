package com.pokemonurpg.core.validation;

public class BetweenValidator implements UrpgValidator {
    private int min;
    private int max;

    public BetweenValidator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(Object input) {
        if (input instanceof Integer) {
            Integer i = (Integer) input;
            return i >= min && i <= max;
        }
        return false;
    }
}
