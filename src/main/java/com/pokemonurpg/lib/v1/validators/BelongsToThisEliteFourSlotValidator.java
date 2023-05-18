package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.services.RequestPathVariableService;
import com.pokemonurpg.lib.v1.annotations.BelongsToThisEliteFourSlot;
import com.pokemonurpg.entities.v1.EliteFour;
import com.pokemonurpg.entities.v1.EliteFourOwnershipTerm;
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
