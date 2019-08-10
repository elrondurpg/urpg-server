package com.pokemonurpg.validator;

import com.pokemonurpg.object.Attack;
import com.pokemonurpg.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;

@Component
public class AttackValidator extends URPGValidator {

    private TypeService typeService;

    @Autowired
    public AttackValidator (TypeService typeService) {
        this.typeService = typeService;
    }

    public Errors validate(Object obj) {
        HashMap<String, String> errorMap = new HashMap<>();
        MapBindingResult errors = new MapBindingResult(errorMap, "");
        validate(obj, errors);
        return errors;
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Attack attack = (Attack) obj;

        if (attack != null) {

            if (!isStringLengthBetween(attack.getName(), 3, 17)) {
                errors.rejectValue("name", "Name '" + attack.getName() + "' is invalid.");
            }

            if (attack.getType() == null || typeService.findByDbid(attack.getType().getDbid()) != null) {
                errors.rejectValue("type", "Type '" + attack.getType() + "' is invalid.");
            }

            if (!isStringLengthBetween(attack.getDescription(), 3, 17)) {
                errors.rejectValue("description", "Description '" + attack.getDescription() + "' is invalid.");
            }

            if (attack.getPower() != null && attack.getPower() < 0) {
                errors.rejectValue("power", "Power '" + attack.getDescription() + "' is invalid.");
            }

            if (attack.getAccuracy() != null && attack.getAccuracy() < 0) {
                errors.rejectValue("accuracy", "Accuracy '" + attack.getDescription() + "' is invalid.");
            }

            if (attack.getPp() != null && attack.getPp() < 0) {
                errors.rejectValue("pp", "PP '" + attack.getDescription() + "' is invalid.");
            }

            if (!isStringLengthBetween(attack.getCategory(), 1, 10)) {
                errors.rejectValue("category", "Attack category '" + attack.getName() + "' is invalid.");
            }

            if (!isStringLengthBetween(attack.getTarget(), 1, 20)) {
                errors.rejectValue("target", "Attack target type '" + attack.getName() + "' is invalid.");
            }

        } else {
            errors.reject("No/Invalid Attack object specified.");
        }
    }
}