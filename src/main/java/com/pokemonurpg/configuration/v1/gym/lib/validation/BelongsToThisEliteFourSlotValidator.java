package com.pokemonurpg.configuration.v1.gym.lib.validation;

import com.pokemonurpg.core.service.RequestPathVariableService;
import com.pokemonurpg.entities.v1.gym.EliteFour;
import com.pokemonurpg.entities.v1.gym.EliteFourOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.service.EliteFourOwnershipTermService;
import com.pokemonurpg.configuration.v1.gym.lib.annotation.BelongsToThisEliteFourSlot;
import com.pokemonurpg.configuration.v1.gym.elitefour.service.EliteFourService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class BelongsToThisEliteFourSlotValidator implements ConstraintValidator<BelongsToThisEliteFourSlot, Integer> {
    @Resource
    private EliteFourService eliteFourService;

    @Resource
    private EliteFourOwnershipTermService eliteFourOwnershipTermService;

    @Resource
    private RequestPathVariableService requestPathVariableService;

    @Override
    public boolean isValid(Integer input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            Integer requestDbid = requestPathVariableService.findIntByName("dbid");
            EliteFour requestEliteFour = eliteFourService.findByDbid(requestDbid);
            if (requestEliteFour != null) {
                EliteFourOwnershipTerm requestTerm = eliteFourOwnershipTermService.findByDbid(input);
                if (requestTerm != null) {
                    EliteFour termEliteFour = requestTerm.getSlot();
                    if (termEliteFour != null) {
                        return Objects.equals(termEliteFour.getDbid(), requestEliteFour.getDbid());
                    }
                }
            }
            return false;
        }
        else return true;
    }
}
