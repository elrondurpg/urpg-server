package com.pokemonurpg.core.validation;

public class LengthBetweenValidator implements UrpgValidator {
    private int min;
    private int max;

    public LengthBetweenValidator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(Object input) {
        if (input instanceof String) {
            String s = (String) input;
            if (s.length() >= min && s.length() <= max) {
                return true;
            }
        }
        return false;
    }
}
