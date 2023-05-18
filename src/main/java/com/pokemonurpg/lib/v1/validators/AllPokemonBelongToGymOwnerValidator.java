package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.services.RequestPathVariableService;
import com.pokemonurpg.lib.v1.annotations.AllPokemonBelongToOwner;
import com.pokemonurpg.configuration.v1.gyms.GymPokemonRequest;
import com.pokemonurpg.entities.v1.Gym;
import com.pokemonurpg.entities.v1.GymLeaderRecord;
import com.pokemonurpg.configuration.v1.gyms.GymService;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.stats.v1.OwnedPokemonService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

public class AllPokemonBelongToGymOwnerValidator extends AllPokemonBelongToOwnerValidator<List<GymPokemonRequest>> {
    @Resource
    private GymService gymService;

    @Resource
    private OwnedPokemonService ownedPokemonService;

    @Resource
    private RequestPathVariableService requestPathVariableService;
    @Override
    public void initialize(AllPokemonBelongToOwner constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<GymPokemonRequest> input, ConstraintValidatorContext context) {
        if (input != null && !input.isEmpty()) {
            Integer requestDbid = requestPathVariableService.findIntByName("dbid");
            Gym requestGym = gymService.findByDbid(requestDbid);

            if (requestGym != null) {
                GymLeaderRecord currentOwnerRecord = requestGym.getCurrentOwnerRecord();
                if (currentOwnerRecord != null) {
                    Member gymOwner = currentOwnerRecord.getOwner();
                    if (gymOwner != null) {
                        for (GymPokemonRequest pokemonInput : input) {
                            if (pokemonInput != null) {
                                OwnedPokemon pokemon = ownedPokemonService.findByDbid(pokemonInput.getDbid());
                                if (pokemon != null) {
                                    Member pokemonOwner = pokemon.getTrainer();
                                    if (pokemonOwner != null) {
                                        if (!Objects.equals(pokemonOwner.getDbid(), gymOwner.getDbid())) {
                                            return false;
                                        }
                                    }
                                    else return false;
                                }
                                else return false;
                            }
                            else return false;
                        }
                        return true;
                    }
                    else return false;
                }
                else return false;
            }
            else return false;
        }
        else return true;
    }
}