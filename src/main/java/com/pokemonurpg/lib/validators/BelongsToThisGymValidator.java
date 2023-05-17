package com.pokemonurpg.lib.validators;

import com.pokemonurpg.lib.service.*;
import com.pokemonurpg.lib.annotations.BelongsToThisGym;
import com.pokemonurpg.entities.Gym;
import com.pokemonurpg.entities.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.gymleaderrecords.GymOwnershipTermService;
import com.pokemonurpg.configuration.v1.gyms.GymService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class BelongsToThisGymValidator implements ConstraintValidator<BelongsToThisGym, Integer> {
    @Resource
    private GymService gymService;

    @Resource
    private GymOwnershipTermService gymOwnershipTermService;

    @Resource
    private RequestPathVariableService requestPathVariableService;

    @Override
    public boolean isValid(Integer input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            Integer requestDbid = requestPathVariableService.findIntByName("dbid");
            Gym requestGym = gymService.findByDbid(requestDbid);
            if (requestGym != null) {
                GymOwnershipTerm requestTerm = gymOwnershipTermService.findByDbid(input);
                if (requestTerm != null) {
                    Gym termGym = requestTerm.getGym();
                    if (termGym != null) {
                        return Objects.equals(termGym.getDbid(), requestGym.getDbid());
                    }
                }
            }
            return false;
        }
        else return true;
    }
}
