package com.pokemonurpg.validator;

import com.pokemonurpg.object.Ability;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;

@Component
public class AbilityValidator extends URPGValidator {

    public Errors validate(Object obj) {
        HashMap<String, String> errorMap = new HashMap<>();
        MapBindingResult errors = new MapBindingResult(errorMap, "");
        validate(obj, errors);
        return errors;
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Ability ability = (Ability) obj;

        if (ability != null) {

            if (!isStringLengthBetween(ability.getName(), 3, 17)) {
                errors.rejectValue("name", "Name '" + ability.getName() + "' is invalid.");
            }

            if (!isStringLengthBetween(ability.getDescription(), 3, 17)) {
                errors.rejectValue("description", "Description '" + ability.getDescription() + "' is invalid.");
            }

        } else {
            errors.reject("No/Invalid Ability object specified.");
        }
    }

}
