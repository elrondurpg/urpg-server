package com.pokemonurpg.gym.validation;

import com.pokemonurpg.core.service.RequestPathVariableService;
import com.pokemonurpg.gym.annotation.BelongsToThisChampionSlot;
import com.pokemonurpg.gym.models.Champion;
import com.pokemonurpg.gym.models.ChampionOwnershipTerm;
import com.pokemonurpg.gym.service.ChampionOwnershipTermService;
import com.pokemonurpg.gym.service.ChampionService;

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
