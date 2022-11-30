package com.pokemonurpg.configuration.v1.gym.lib.validation;

import com.pokemonurpg.core.service.RequestPathVariableService;
import com.pokemonurpg.configuration.v1.gym.elitefour.model.EliteFour;
import com.pokemonurpg.configuration.v1.gym.elitefour.input.EliteFourPokemonInputDto;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.model.EliteFourOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.lib.annotation.AllPokemonBelongToOwner;
import com.pokemonurpg.configuration.v1.gym.elitefour.service.EliteFourService;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

public class AllPokemonBelongToEliteFourMemberValidator extends AllPokemonBelongToOwnerValidator<List<EliteFourPokemonInputDto>> {
    @Resource
    private EliteFourService eliteFourService;

    @Resource
    private OwnedPokemonService ownedPokemonService;

    @Resource
    private RequestPathVariableService requestPathVariableService;
    @Override
    public void initialize(AllPokemonBelongToOwner constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<EliteFourPokemonInputDto> input, ConstraintValidatorContext context) {
        if (input != null && !input.isEmpty()) {
            Integer requestDbid = requestPathVariableService.findIntByName("dbid");
            EliteFour requestEliteFour = eliteFourService.findByDbid(requestDbid);

            if (requestEliteFour != null) {
                EliteFourOwnershipTerm currentOwnerRecord = requestEliteFour.getCurrentOwnerRecord();
                if (currentOwnerRecord != null) {
                    Member eliteFourOwner = currentOwnerRecord.getOwner();
                    if (eliteFourOwner != null) {
                        for (EliteFourPokemonInputDto pokemonInput : input) {
                            if (pokemonInput != null) {
                                OwnedPokemon pokemon = ownedPokemonService.findByDbid(pokemonInput.getDbid());
                                if (pokemon != null) {
                                    Member pokemonOwner = pokemon.getTrainer();
                                    if (pokemonOwner != null) {
                                        if (!Objects.equals(pokemonOwner.getDbid(), eliteFourOwner.getDbid())) {
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