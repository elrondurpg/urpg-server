package com.pokemonurpg.gym.validation;

import com.pokemonurpg.core.service.*;
import com.pokemonurpg.gym.annotation.BelongsToThisGym;
import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.model.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.service.GymOwnershipTermService;
import com.pokemonurpg.gym.service.GymService;

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
