package com.pokemonurpg.lib.validation.validator;

import com.pokemonurpg.lib.service.*;
import com.pokemonurpg.lib.validation.annotation.UniqueDiscordId;
import com.pokemonurpg.configuration.v1.members.MemberInputDto;
import com.pokemonurpg.entities.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueDiscordIdValidator implements ConstraintValidator<UniqueDiscordId, MemberInputDto> {
    @Resource
    private MemberService memberService;

    @Resource
    private RequestPathVariableService requestPathVariableService;

    @Override
    public boolean isValid(MemberInputDto input, ConstraintValidatorContext constraintValidatorContext) {
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
