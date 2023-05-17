package com.pokemonurpg.lib.validators;

import com.pokemonurpg.lib.service.RequestPathVariableService;
import com.pokemonurpg.lib.annotations.BelongsToThisEliteFourSlot;
import com.pokemonurpg.entities.EliteFour;
import com.pokemonurpg.entities.EliteFourOwnershipTerm;
import com.pokemonurpg.configuration.v1.elitefourrecords.EliteFourOwnershipTermService;
import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourService;

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
