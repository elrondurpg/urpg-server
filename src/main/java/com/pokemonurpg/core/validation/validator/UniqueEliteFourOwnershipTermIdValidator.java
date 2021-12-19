package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.core.validation.annotation.UniqueId;
import com.pokemonurpg.gym.input.EliteFourOwnershipTermInputDto;
import com.pokemonurpg.gym.input.GymOwnershipTermInputDto;
import com.pokemonurpg.gym.service.EliteFourOwnershipTermService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidatorContext;

public class UniqueEliteFourOwnershipTermIdValidator extends UniqueValidator<EliteFourOwnershipTermInputDto> {

    @Resource
    private EliteFourOwnershipTermService eliteFourOwnershipTermService;

    @Override
    public void initialize(UniqueId constraintAnnotation) {

    }

    @Override
    public boolean isValid(EliteFourOwnershipTermInputDto input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            return eliteFourOwnershipTermService.findBySlotAndOwnerAndOpenDate(input.getSlot(), input.getOwner(), input.getOpenDate()) == null;
        }
        else return true;
    }
}
