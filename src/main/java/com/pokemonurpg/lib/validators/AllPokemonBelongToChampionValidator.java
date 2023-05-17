package com.pokemonurpg.lib.validators;

import com.pokemonurpg.lib.service.RequestPathVariableService;
import com.pokemonurpg.lib.annotations.AllPokemonBelongToOwner;
import com.pokemonurpg.configuration.v1.championslots.ChampionPokemonInputDto;
import com.pokemonurpg.entities.Champion;
import com.pokemonurpg.entities.ChampionOwnershipTerm;
import com.pokemonurpg.configuration.v1.championslots.ChampionService;
import com.pokemonurpg.entities.Member;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

public class AllPokemonBelongToChampionValidator extends AllPokemonBelongToOwnerValidator<List<ChampionPokemonInputDto>> {
    @Resource
    private ChampionService championService;

    @Resource
    private OwnedPokemonService ownedPokemonService;

    @Resource
    private RequestPathVariableService requestPathVariableService;
    @Override
    public void initialize(AllPokemonBelongToOwner constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<ChampionPokemonInputDto> input, ConstraintValidatorContext context) {
        if (input != null && !input.isEmpty()) {
            Integer requestDbid = requestPathVariableService.findIntByName("dbid");
            Champion requestChampion = championService.findByDbid(requestDbid);

            if (requestChampion != null) {
                ChampionOwnershipTerm currentOwnerRecord = requestChampion.getCurrentOwnerRecord();
                if (currentOwnerRecord != null) {
                    Member championOwner = currentOwnerRecord.getOwner();
                    if (championOwner != null) {
                        for (ChampionPokemonInputDto pokemonInput : input) {
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