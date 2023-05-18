package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.annotations.UniqueId;
import com.pokemonurpg.configuration.v1.championrecords.ChampionRecordRequest;
import com.pokemonurpg.configuration.v1.championrecords.ChampionRecordService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidatorContext;

public class UniqueChampionRecordIdValidator extends UniqueValidator<ChampionRecordRequest> {

    @Resource
    private ChampionRecordService championRecordService;

    @Override
    public void initialize(UniqueId constraintAnnotation) {

    }

    @Override
    public boolean isValid(ChampionRecordRequest input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            return championRecordService.findBySlotAndOwnerAndOpenDate(input.getSlot(), input.getOwner(), input.getOpenDate()) == null;
        }
        else return true;
    }
}
