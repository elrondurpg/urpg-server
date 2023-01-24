package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.lib.validator.EntityValidator;
import com.pokemonurpg.v2.entities.pokemon.Type;

public class TypeValidatorFake extends EntityValidator<Type> {
    public final static String VALID_NAME = "VALID_NAME";
    public final static String INVALID_NAME = "INVALID_NAME";
    public final static String ERROR = "ERROR";

    @Override
    public boolean isValid(Type input) {
        if (VALID_NAME.equals(input.getName())) {
            return true;
        }
        else {
            errors.add(ERROR);
            return false;
        }
    }
}
