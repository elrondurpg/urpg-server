package com.pokemonurpg.configuration.v1.gym.lib.validation;

import com.pokemonurpg.core.service.RequestPathVariableService;
import com.pokemonurpg.entities.v1.gym.Champion;
import com.pokemonurpg.entities.v1.gym.ChampionOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.service.ChampionOwnershipTermService;
import com.pokemonurpg.configuration.v1.gym.lib.annotation.BelongsToThisChampionSlot;
import com.pokemonurpg.configuration.v1.gym.champion.service.ChampionService;

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
