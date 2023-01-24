package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.domain.lib.validator.EntityValidator;
import com.pokemonurpg.v2.entities.pokemon.Type;

public class TypeValidatorFake extends EntityValidator<Type> {
    public final static String VALID_NAME = "VALID_NAME";
    public final static String INVALID_NAME = "INVALID_NAME";

    @Override
    public boolean isValid(Type input) {
        return VALID_NAME.equals(input.getName());
    }
}
