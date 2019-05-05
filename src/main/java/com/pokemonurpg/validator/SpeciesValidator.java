package com.pokemonurpg.validator;

import com.pokemonurpg.object.Species;
import com.pokemonurpg.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;

@Component
public class SpeciesValidator extends URPGValidator {

    @Autowired
    private TypeService typeService;

    public Errors validate(Object obj) {
        HashMap<String, String> errorMap = new HashMap<>();
        MapBindingResult errors = new MapBindingResult(errorMap, "");
        validate(obj, errors);
        return errors;
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Species pokemon = (Species) obj;

        if (pokemon != null) {

            if (emptyInputString(pokemon.getName()) || !isIntegerBetween(pokemon.getName().length(), 3, 21)) {
                errors.rejectValue("name", "Name '" + pokemon.getName() + "' is invalid.");
            }

            if (pokemon.getDexno() == null || !isIntegerBetween(pokemon.getDexno(), 1, 1000)) {
                errors.rejectValue("dexno", "Dex No. '" + pokemon.getDexno() + "' is invalid.");
            }

            if (pokemon.getType1() == null || !typeService.findByDbid(pokemon.getType1().getDbid()).isPresent() )
            {
                errors.rejectValue("type1", "Type 1 '" + pokemon.getType1() + "' is invalid.");
            }

            if (pokemon.getType2() != null && !typeService.findByDbid(pokemon.getType2().getDbid()).isPresent() )
            {
                errors.rejectValue("type2", "Type 2 '" + pokemon.getType2() + "' is invalid.");
            }

            if (!emptyInputString(pokemon.getClassification()) && !isIntegerBetween(pokemon.getClassification().length(), 1, 20)) {
                errors.rejectValue("classification", "Name '" + pokemon.getClassification() + "' is invalid.");
            }

            if (pokemon.getHp() == null || !isIntegerBetween(pokemon.getHp(), 1, 1000)) {
                errors.rejectValue("hp", "HP '" + pokemon.getHp() + "' is invalid.");
            }

            if (pokemon.getAttack() == null || !isIntegerBetween(pokemon.getAttack(), 1, 1000)) {
                errors.rejectValue("attack", "Attack '" + pokemon.getAttack() + "' is invalid.");
            }

            if (pokemon.getDefense() == null || !isIntegerBetween(pokemon.getDefense(), 1, 1000)) {
                errors.rejectValue("defense", "HP '" + pokemon.getDefense() + "' is invalid.");
            }

            if (pokemon.getSpecialAttack() == null || !isIntegerBetween(pokemon.getSpecialAttack(), 1, 1000)) {
                errors.rejectValue("specialAttack", "Special Attack '" + pokemon.getSpecialAttack() + "' is invalid.");
            }

            if (pokemon.getSpecialDefense() == null || !isIntegerBetween(pokemon.getSpecialDefense(), 1, 1000)) {
                errors.rejectValue("specialDefense", "Special Defense '" + pokemon.getSpecialDefense() + "' is invalid.");
            }

            if (pokemon.getSpeed() == null || !isIntegerBetween(pokemon.getSpeed(), 1, 1000)) {
                errors.rejectValue("speed", "Speed '" + pokemon.getSpeed() + "' is invalid.");
            }

            if (pokemon.getHeight() != null && !isDoubleBetween(pokemon.getHeight(), 0.1, 1000)) {
                errors.rejectValue("height", "Height '" + pokemon.getHeight() + "' is invalid.");
            }

            if (pokemon.getWeight() != null && !isDoubleBetween(pokemon.getWeight(), 0.1, 2000)) {
                errors.rejectValue("weight", "Weight '" + pokemon.getWeight() + "' is invalid.");
            }

            if (!emptyInputString(pokemon.getPokemart()) && !isIntegerBetween(pokemon.getPokemart().length(), 1, 8)) {
                errors.rejectValue("pokemart", "Pokemart value '" + pokemon.getPokemart() + "' is invalid.");
            }

            if (!emptyInputString(pokemon.getContestCredits()) && !isIntegerBetween(pokemon.getContestCredits().length(), 1, 8)) {
                errors.rejectValue("contestCredits", "Contest credit value '" + pokemon.getContestCredits() + "' is invalid.");
            }

            if (pokemon.getArtRank() != null && !isIntegerBetween(pokemon.getArtRank(), 1, 8)) {
                errors.rejectValue("artRank", "Art Rank DBID '" + pokemon.getArtRank() + "' is invalid.");
            }

            if (pokemon.getStoryRank() != null && !isIntegerBetween(pokemon.getStoryRank(), 1, 8)) {
                errors.rejectValue("storyRank", "Story Rank DBID '" + pokemon.getStoryRank() + "' is invalid.");
            }

            if (pokemon.getParkRank() != null && !isIntegerBetween(pokemon.getParkRank(), 1, 7)) {
                errors.rejectValue("parkRank", "Park Rank DBID '" + pokemon.getParkRank() + "' is invalid.");
            }

            if (pokemon.getParkLocation() != null && !isIntegerBetween(pokemon.getParkLocation(), 1, 9)) {
                errors.rejectValue("parkLocation", "Park Location DBID '" + pokemon.getParkLocation() + "' is invalid.");
            }

            if (emptyInputString(pokemon.getDisplayName())) {
                errors.rejectValue("displayName", "Display Name '" + pokemon.getDisplayName() + "' is invalid.");
            }

            if (!emptyInputString(pokemon.getFormName()) && !isIntegerBetween(pokemon.getFormName().length(), 2, 20)) {
                errors.rejectValue("formName", "Form Name '" + pokemon.getFormName() + "' is invalid.");
            }
        }
        else {
            errors.reject("No/Invalid Pokemon object specified.");
        }

    }

}
