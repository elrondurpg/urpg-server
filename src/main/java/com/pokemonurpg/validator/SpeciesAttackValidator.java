package com.pokemonurpg.validator;

import com.pokemonurpg.URPGServerApplication;
import com.pokemonurpg.object.Attack;
import com.pokemonurpg.object.Species;
import com.pokemonurpg.object.SpeciesAttack;
import com.pokemonurpg.service.AttackService;
import com.pokemonurpg.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Component
public class SpeciesAttackValidator extends URPGValidator {

    private SpeciesService speciesService;

    private AttackService attackService;

    private ArrayList<String> validMethods;

    @Autowired
    public SpeciesAttackValidator (SpeciesService speciesService, AttackService attackService) {
        this.speciesService = speciesService;
        this.attackService = attackService;
        String[] temp = { "LEVEL-UP", "TM", "HM", "BREEDING", "MOVE TUTOR", "SPECIAL" };
        validMethods = new ArrayList<>(Arrays.asList(temp));
    }

    public Errors validate(Object obj) {
        HashMap<String, String> errorMap = new HashMap<>();
        MapBindingResult errors = new MapBindingResult(errorMap, "");
        validate(obj, errors);
        return errors;
    }

    @Override
    public void validate(Object obj, Errors errors) {
        SpeciesAttack speciesAttack = (SpeciesAttack) obj;
/* TODO reinstate
        if (speciesAttack != null) {
            Species species = null;
            Attack attack = null;
            if (speciesAttack.internalGetId() == null) {
                species = speciesAttack.getSpecies();
                attack = speciesAttack.getAttack();
            }
            else {
                species = speciesService.findByDbid(speciesAttack.internalGetId().getSpeciesDbid()).get();
                attack = attackService.findByDbid(speciesAttack.internalGetId().getAttackDbid()).get();
            }

            if (species == null || !speciesService.findByName(species.getName()).isPresent()) {
                errors.rejectValue("species", "Species '" + species.getName() + "' is invalid.");
            }

            if (attack == null || !attackService.findByName(attack.getName()).isPresent()) {
                errors.rejectValue("attack", "Attack '" + attack.getName() + "' is invalid.");
            }

            String method = speciesAttack.getMethod();
            if (method == null || !validMethods.contains(method.toUpperCase())) {
                errors.rejectValue("method", "Attack learning method '" + method + "' is invalid.");
            }
            else {
                if ("TM".equals(method.toUpperCase()) && speciesAttack.getGeneration() == null) {
                    errors.rejectValue("generation", "TM learning method requires generation.");
                }
            }

            Integer generation = speciesAttack.getGeneration();
            if (generation != null && !isIntegerBetween(generation, 1, URPGServerApplication.CURRENT_GEN)) {
                errors.rejectValue("generation", "Generation '" + generation + "' is invalid.");
            }
        }
        else {
            errors.reject("No/Invalid Pokemon object specified.");
        }
*/
    }

}
