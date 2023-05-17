package com.pokemonurpg.lib.validation.validator;

import com.pokemonurpg.lib.validation.annotation.UniqueId;
import com.pokemonurpg.configuration.v1.gymleaderrecords.GymOwnershipTermInputDto;
import com.pokemonurpg.configuration.v1.gymleaderrecords.GymOwnershipTermService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidatorContext;

public class UniqueGymOwnershipTermIdValidator extends UniqueValidator<GymOwnershipTermInputDto> {

    @Resource
    private GymOwnershipTermService gymOwnershipTermService;

    @Override
    public void initialize(UniqueId constraintAnnotation) {

    }

    @Override
    public boolean isValid(GymOwnershipTermInputDto input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            return gymOwnershipTermService.findByGymAndOwnerAndOpenDate(input.getGym(), input.getOwner(), input.getOpenDate()) == null;
        }
        else return true;
    }
}
