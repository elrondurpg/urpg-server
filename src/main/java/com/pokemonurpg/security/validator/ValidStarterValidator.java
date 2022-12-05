package com.pokemonurpg.security.validator;

import com.pokemonurpg.account.v1.register.beginner.internal.CreateStarterPokemonRequest;
import com.pokemonurpg.entities.v1.pokemon.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesService;
import com.pokemonurpg.security.annotation.ValidStarter;
import com.pokemonurpg.stats.validation.OwnedPokemonValidator;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidStarterValidator implements ConstraintValidator<ValidStarter, CreateStarterPokemonRequest> {
    @Resource
    private SpeciesService speciesService;

    @Resource
    private OwnedPokemonValidator ownedPokemonValidator;

    @Override
    public void initialize(ValidStarter constraint) {

    }

    @Override
    public boolean isValid(CreateStarterPokemonRequest input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            Species species = speciesService.findByName(input.getSpecies());
            return ownedPokemonValidator.isGenderLegal(species, input.getGender()) && ownedPokemonValidator.isValidStarter(species);
        }
        else return true;
    }
}