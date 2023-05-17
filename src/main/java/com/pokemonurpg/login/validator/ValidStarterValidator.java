package com.pokemonurpg.login.validator;

import com.pokemonurpg.login.annotation.ValidStarter;
import com.pokemonurpg.entities.Species;
import com.pokemonurpg.configuration.v1.pokemon.SpeciesService;
import com.pokemonurpg.stats.input.StarterPokemonInputDto;
import com.pokemonurpg.stats.validation.OwnedPokemonValidator;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidStarterValidator implements ConstraintValidator<ValidStarter, StarterPokemonInputDto> {
    @Resource
    private SpeciesService speciesService;

    @Resource
    private OwnedPokemonValidator ownedPokemonValidator;

    @Override
    public void initialize(ValidStarter constraint) {

    }

    @Override
    public boolean isValid(StarterPokemonInputDto input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            Species species = speciesService.findByName(input.getSpecies());
            return ownedPokemonValidator.isGenderLegal(species, input.getGender()) && ownedPokemonValidator.isValidStarter(species);
        }
        else return true;
    }
}