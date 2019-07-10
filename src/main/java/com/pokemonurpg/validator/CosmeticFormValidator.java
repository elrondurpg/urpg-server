package com.pokemonurpg.validator;

import com.pokemonurpg.object.CosmeticForm;
import com.pokemonurpg.object.Species;
import com.pokemonurpg.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;
import java.util.Optional;

@Component
public class CosmeticFormValidator extends URPGValidator {

    private SpeciesService speciesService;

    @Autowired
    public CosmeticFormValidator (SpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    public Errors validate(Object obj) {
        HashMap<String, String> errorMap = new HashMap<>();
        MapBindingResult errors = new MapBindingResult(errorMap, "");
        validate(obj, errors);
        return errors;
    }

    @Override
    public void validate(Object obj, Errors errors) {
        CosmeticForm cosmeticForm = (CosmeticForm) obj;
/* TODO reinstate
        if (cosmeticForm != null) {

            if (cosmeticForm.getSpecies() != null) {
                Optional<Species> speciesOptional = speciesService.findByDbid(cosmeticForm.getSpecies().getDbid());
                if (!speciesOptional.isPresent()) {
                    errors.rejectValue("dbid", "DBID '" + cosmeticForm.getSpecies().getDbid() + "' does not correspond to an existing species.");
                }
            }
            else {
                errors.rejectValue("species", "Species cannot be null.");
            }

            if (!isStringLengthBetween(cosmeticForm.getName(), 3, 20)) {
                errors.rejectValue("name", "Name '" + cosmeticForm.getName() + "' is invalid.");
            }

            if (!isStringLengthBetween(cosmeticForm.getDisplayName(), 3, 20)) {
                errors.rejectValue("displayName", "Display name '" + cosmeticForm.getDisplayName() + "' is invalid.");
            }

        } else {
            errors.reject("No/Invalid CosmeticForm object specified.");
        }*/
    }
}