package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.configuration.v1.members.MemberRequest;
import com.pokemonurpg.configuration.v1.members.MemberService;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.lib.v1.annotations.UniqueDiscordId;
import com.pokemonurpg.lib.v1.services.RequestPathVariableService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueDiscordIdValidator implements ConstraintValidator<UniqueDiscordId, MemberRequest> {
    @Resource
    private MemberService memberService;

    @Resource
    private RequestPathVariableService requestPathVariableService;

    @Override
    public boolean isValid(MemberRequest input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            String discordId = input.getDiscordId();
            if (discordId != null) {
                Member existingObject = memberService.findByDiscordId(discordId);

                if (existingObject != null) {
                    Integer requestDbid = requestPathVariableService.findIntByName("dbid");
                    Integer objectDbid = existingObject.getDbid();
                    return requestDbid != null && objectDbid == (int) requestDbid;
                }
                else return true;
            }
            else return true;
        }
        else return true;
    }
}
