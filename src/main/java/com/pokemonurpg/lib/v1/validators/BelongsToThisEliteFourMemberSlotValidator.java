package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.services.RequestPathVariableService;
import com.pokemonurpg.lib.v1.annotations.BelongsToThisEliteFourMemberSlot;
import com.pokemonurpg.entities.v1.EliteFourMemberSlot;
import com.pokemonurpg.entities.v1.EliteFourMemberRecord;
import com.pokemonurpg.configuration.v1.elitefourrecords.EliteFourRecordService;
import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourMemberSlotService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class BelongsToThisEliteFourMemberSlotValidator implements ConstraintValidator<BelongsToThisEliteFourMemberSlot, Integer> {
    @Resource
    private EliteFourMemberSlotService eliteFourMemberSlotService;

    @Resource
    private EliteFourRecordService eliteFourRecordService;

    @Resource
    private RequestPathVariableService requestPathVariableService;

    @Override
    public boolean isValid(Integer input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            Integer requestDbid = requestPathVariableService.findIntByName("dbid");
            EliteFourMemberSlot requestEliteFourMemberSlot = eliteFourMemberSlotService.findByDbid(requestDbid);
            if (requestEliteFourMemberSlot != null) {
                EliteFourMemberRecord requestTerm = eliteFourRecordService.findByDbid(input);
                if (requestTerm != null) {
                    EliteFourMemberSlot termEliteFourMemberSlot = requestTerm.getSlot();
                    if (termEliteFourMemberSlot != null) {
                        return Objects.equals(termEliteFourMemberSlot.getDbid(), requestEliteFourMemberSlot.getDbid());
                    }
                }
            }
            return false;
        }
        else return true;
    }
}
