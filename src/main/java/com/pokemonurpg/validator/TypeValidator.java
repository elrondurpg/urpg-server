package com.pokemonurpg.validator;

import com.pokemonurpg.object.Species;
import com.pokemonurpg.object.Type;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;

@Component
public class TypeValidator extends URPGValidator  {

    public Errors validate(Object obj) {
        HashMap<String, String> errorMap = new HashMap<>();
        MapBindingResult errors = new MapBindingResult(errorMap, "");
        validate(obj, errors);
        return errors;
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Type type = (Type) obj;

        if (type != null) {
            if (emptyInputString(type.getName()) || !isIntegerBetween(type.getName().length(), 3, 10)) {
                errors.rejectValue("name", "Name '" + type.getName() + "' is invalid.");
            }
        }
        else {
            errors.reject("No/Invalid Type object specified.");
        }
    }
}
