package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.annotations.UniqueId;
import com.pokemonurpg.configuration.v1.elitefourrecords.EliteFourRecordRequest;
import com.pokemonurpg.configuration.v1.elitefourrecords.EliteFourRecordService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidatorContext;

public class UniqueEliteFourRecordIdValidator extends UniqueValidator<EliteFourRecordRequest> {

    @Resource
    private EliteFourRecordService eliteFourRecordService;

    @Override
    public void initialize(UniqueId constraintAnnotation) {

    }

    @Override
    public boolean isValid(EliteFourRecordRequest input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            return eliteFourRecordService.findBySlotAndOwnerAndOpenDate(input.getSlot(), input.getOwner(), input.getOpenDate()) == null;
        }
        else return true;
    }
}
