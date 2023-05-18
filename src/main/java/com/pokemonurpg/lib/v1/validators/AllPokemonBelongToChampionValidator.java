package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.services.RequestPathVariableService;
import com.pokemonurpg.lib.v1.annotations.AllPokemonBelongToOwner;
import com.pokemonurpg.configuration.v1.championslots.ChampionPokemonRequest;
import com.pokemonurpg.entities.v1.ChampionSlot;
import com.pokemonurpg.entities.v1.ChampionRecord;
import com.pokemonurpg.configuration.v1.championslots.ChampionSlotService;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.stats.v1.OwnedPokemonService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

public class AllPokemonBelongToChampionValidator extends AllPokemonBelongToOwnerValidator<List<ChampionPokemonRequest>> {
    @Resource
    private ChampionSlotService championSlotService;

    @Resource
    private OwnedPokemonService ownedPokemonService;

    @Resource
    private RequestPathVariableService requestPathVariableService;
    @Override
    public void initialize(AllPokemonBelongToOwner constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<ChampionPokemonRequest> input, ConstraintValidatorContext context) {
        if (input != null && !input.isEmpty()) {
            Integer requestDbid = requestPathVariableService.findIntByName("dbid");
            ChampionSlot requestChampionSlot = championSlotService.findByDbid(requestDbid);

            if (requestChampionSlot != null) {
                ChampionRecord currentOwnerRecord = requestChampionSlot.getCurrentOwnerRecord();
                if (currentOwnerRecord != null) {
                    Member championOwner = currentOwnerRecord.getOwner();
                    if (championOwner != null) {
                        for (ChampionPokemonRequest pokemonInput : input) {
                            if (pokemonInput != null) {
                                OwnedPokemon pokemon = ownedPokemonService.findByDbid(pokemonInput.getDbid());
                                if (pokemon != null) {
                                    Member pokemonOwner = pokemon.getTrainer();
                                    if (pokemonOwner != null) {
                                        if (!Objects.equals(pokemonOwner.getDbid(), championOwner.getDbid())) {
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