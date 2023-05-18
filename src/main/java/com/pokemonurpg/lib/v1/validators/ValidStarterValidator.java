package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.annotations.ValidStarter;
import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.configuration.v1.pokemon.PokemonService;
import com.pokemonurpg.stats.v1.StarterPokemonRequest;
import com.pokemonurpg.stats.v1.OwnedPokemonValidator;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidStarterValidator implements ConstraintValidator<ValidStarter, StarterPokemonRequest> {
    @Resource
    private PokemonService pokemonService;

    @Resource
    private OwnedPokemonValidator ownedPokemonValidator;

    @Override
    public void initialize(ValidStarter constraint) {

    }

    @Override
    public boolean isValid(StarterPokemonRequest input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            Pokemon pokemon = pokemonService.findByName(input.getSpecies());
            return ownedPokemonValidator.isGenderLegal(pokemon, input.getGender()) && ownedPokemonValidator.isValidStarter(pokemon);
        }
        else return true;
    }
}