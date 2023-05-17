package com.pokemonurpg.lib.validation.validator;

import com.pokemonurpg.lib.validation.annotation.UniqueId;
import com.pokemonurpg.configuration.v1.elitefourrecords.EliteFourOwnershipTermInputDto;
import com.pokemonurpg.configuration.v1.elitefourrecords.EliteFourOwnershipTermService;

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
