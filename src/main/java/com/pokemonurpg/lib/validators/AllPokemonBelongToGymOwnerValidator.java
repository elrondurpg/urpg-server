package com.pokemonurpg.lib.validators;

import com.pokemonurpg.lib.service.RequestPathVariableService;
import com.pokemonurpg.lib.annotations.AllPokemonBelongToOwner;
import com.pokemonurpg.configuration.v1.gyms.GymPokemonInputDto;
import com.pokemonurpg.entities.Gym;
import com.pokemonurpg.entities.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.gyms.GymService;
import com.pokemonurpg.entities.Member;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

public class AllPokemonBelongToGymOwnerValidator extends AllPokemonBelongToOwnerValidator<List<GymPokemonInputDto>> {
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
    public boolean isValid(List<GymPokemonInputDto> input, ConstraintValidatorContext context) {
        if (input != null && !input.isEmpty()) {
            Integer requestDbid = requestPathVariableService.findIntByName("dbid");
            Gym requestGym = gymService.findByDbid(requestDbid);

            if (requestGym != null) {
                GymOwnershipTerm currentOwnerRecord = requestGym.getCurrentOwnerRecord();
                if (currentOwnerRecord != null) {
                    Member gymOwner = currentOwnerRecord.getOwner();
                    if (gymOwner != null) {
                        for (GymPokemonInputDto pokemonInput : input) {
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