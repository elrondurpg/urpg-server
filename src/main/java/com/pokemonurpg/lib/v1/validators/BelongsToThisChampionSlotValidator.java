package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.services.RequestPathVariableService;
import com.pokemonurpg.lib.v1.annotations.BelongsToThisChampionSlot;
import com.pokemonurpg.entities.v1.ChampionSlot;
import com.pokemonurpg.entities.v1.ChampionRecord;
import com.pokemonurpg.configuration.v1.championrecords.ChampionRecordService;
import com.pokemonurpg.configuration.v1.championslots.ChampionSlotService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class BelongsToThisChampionSlotValidator implements ConstraintValidator<BelongsToThisChampionSlot, Integer> {
    @Resource
    private ChampionSlotService championSlotService;

    @Resource
    private ChampionRecordService championRecordService;

    @Resource
    private RequestPathVariableService requestPathVariableService;

    @Override
    public boolean isValid(Integer input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            Integer requestDbid = requestPathVariableService.findIntByName("dbid");
            ChampionSlot requestChampionSlot = championSlotService.findByDbid(requestDbid);
            if (requestChampionSlot != null) {
                ChampionRecord requestTerm = championRecordService.findByDbid(input);
                if (requestTerm != null) {
                    ChampionSlot termChampionSlot = requestTerm.getSlot();
                    if (termChampionSlot != null) {
                        return Objects.equals(termChampionSlot.getDbid(), requestChampionSlot.getDbid());
                    }
                }
            }
            return false;
        }
        else return true;
    }
}
