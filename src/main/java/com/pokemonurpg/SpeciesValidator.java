package com.pokemonurpg;

import com.pokemonurpg.object.Species;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SpeciesValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Species.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Species pokemon = (Species) obj;
        if (emptyInputString(pokemon.getName())) {
            errors.rejectValue("name", "name.empty");
        }
    }

    private boolean emptyInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }

}
