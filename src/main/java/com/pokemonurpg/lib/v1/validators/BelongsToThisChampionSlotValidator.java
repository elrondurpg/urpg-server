package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.services.RequestPathVariableService;
import com.pokemonurpg.lib.v1.annotations.BelongsToThisChampionSlot;
import com.pokemonurpg.entities.v1.Champion;
import com.pokemonurpg.entities.v1.ChampionOwnershipTerm;
import com.pokemonurpg.configuration.v1.championrecords.ChampionOwnershipTermService;
import com.pokemonurpg.configuration.v1.championslots.ChampionService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class BelongsToThisChampionSlotValidator implements ConstraintValidator<BelongsToThisChampionSlot, Integer> {
    @Resource
    private ChampionService championService;

    @Resource
    private ChampionOwnershipTermService championOwnershipTermService;

    @Resource
    private RequestPathVariableService requestPathVariableService;

    @Override
    public boolean isValid(Integer input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            Integer requestDbid = requestPathVariableService.findIntByName("dbid");
            Champion requestChampion = championService.findByDbid(requestDbid);
            if (requestChampion != null) {
                ChampionOwnershipTerm requestTerm = championOwnershipTermService.findByDbid(input);
                if (requestTerm != null) {
                    Champion termChampion = requestTerm.getSlot();
                    if (termChampion != null) {
                        return Objects.equals(termChampion.getDbid(), requestChampion.getDbid());
                    }
                }
            }
            return false;
        }
        else return true;
    }
}
