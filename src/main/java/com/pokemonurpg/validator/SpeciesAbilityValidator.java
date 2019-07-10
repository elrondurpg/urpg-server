package com.pokemonurpg.validator;

import com.pokemonurpg.URPGServerApplication;
import com.pokemonurpg.object.Ability;
import com.pokemonurpg.object.Species;
import com.pokemonurpg.object.SpeciesAbility;
import com.pokemonurpg.service.AbilityService;
import com.pokemonurpg.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Component
public class SpeciesAbilityValidator extends URPGValidator {

    private SpeciesService speciesService;

    private AbilityService abilityService;

    @Autowired
    public SpeciesAbilityValidator (SpeciesService speciesService, AbilityService abilityService) {
        this.speciesService = speciesService;
        this.abilityService = abilityService;
    }

    public Errors validate(Object obj) {
        HashMap<String, String> errorMap = new HashMap<>();
        MapBindingResult errors = new MapBindingResult(errorMap, "");
        validate(obj, errors);
        return errors;
    }

    @Override
    public void validate(Object obj, Errors errors) {
        SpeciesAbility speciesAbility = (SpeciesAbility) obj;
/* TODO reinstate
        if (speciesAbility != null) {
            Species species = null;
            Ability ability = null;
            if (speciesAbility.internalGetId() == null) {
                species = speciesAbility.getSpecies();
                ability = speciesAbility.getAbility();
            }
            else {
                species = speciesService.findByDbid(speciesAbility.internalGetId().getSpeciesDbid()).get();
                ability = abilityService.findByDbid(speciesAbility.internalGetId().getAbilityDbid()).get();
            }

            if (species == null || !speciesService.findByName(species.getName()).isPresent()) {
                errors.rejectValue("species", "Species '" + species.getName() + "' is invalid.");
            }

            if (ability == null || !abilityService.findByName(ability.getName()).isPresent()) {
                errors.rejectValue("ability", "Ability '" + ability.getName() + "' is invalid.");
            }

            Boolean hidden = speciesAbility.getHidden();
            if (hidden == null) {
                errors.rejectValue("hidden", "'Hidden' cannot be null.");
            }
        }
        else {
            errors.reject("No/Invalid Pokemon object specified.");
        }*/

    }
    
}
