package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.annotations.UniqueId;
import com.pokemonurpg.configuration.v1.gymleaderrecords.GymLeaderRecordRequest;
import com.pokemonurpg.configuration.v1.gymleaderrecords.GymLeaderRecordService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidatorContext;

public class UniqueGymLeaderRecordIdValidator extends UniqueValidator<GymLeaderRecordRequest> {

    @Resource
    private GymLeaderRecordService gymLeaderRecordService;

    @Override
    public void initialize(UniqueId constraintAnnotation) {

    }

    @Override
    public boolean isValid(GymLeaderRecordRequest input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            return gymLeaderRecordService.findByGymAndOwnerAndOpenDate(input.getGym(), input.getOwner(), input.getOpenDate()) == null;
        }
        else return true;
    }
}
