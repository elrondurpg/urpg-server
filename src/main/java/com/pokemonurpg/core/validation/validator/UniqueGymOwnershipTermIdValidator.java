package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.core.service.RequestPathVariableService;
import com.pokemonurpg.core.validation.annotation.UniqueId;
import com.pokemonurpg.gym.input.GymOwnershipTermInputDto;
import com.pokemonurpg.gym.service.GymOwnershipTermService;
import com.pokemonurpg.gym.service.GymService;
import com.pokemonurpg.member.service.MemberService;

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
