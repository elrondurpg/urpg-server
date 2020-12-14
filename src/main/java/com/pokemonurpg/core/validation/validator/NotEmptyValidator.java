package com.pokemonurpg.core.validation.validator;

public class NotNullValidator implements UrpgValidator {
    @Override
    public boolean isValid(Object input) {
        return input != null;
    }
}
