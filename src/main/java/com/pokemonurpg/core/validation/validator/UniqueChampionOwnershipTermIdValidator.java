package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.core.validation.annotation.UniqueId;
import com.pokemonurpg.gym.input.ChampionOwnershipTermInputDto;
import com.pokemonurpg.gym.service.ChampionOwnershipTermService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidatorContext;

public class UniqueChampionOwnershipTermIdValidator extends UniqueValidator<ChampionOwnershipTermInputDto> {

    @Resource
    private ChampionOwnershipTermService championOwnershipTermService;

    @Override
    public void initialize(UniqueId constraintAnnotation) {

    }

    @Override
    public boolean isValid(ChampionOwnershipTermInputDto input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            return championOwnershipTermService.findBySlotAndOwnerAndOpenDate(input.getSlot(), input.getOwner(), input.getOpenDate()) == null;
        }
        else return true;
    }
}
