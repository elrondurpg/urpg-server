package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.core.service.*;
import com.pokemonurpg.core.validation.annotation.UniqueDiscordId;
import com.pokemonurpg.configuration.v1.member.member.input.MemberInputDto;
import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.configuration.v1.member.member.service.MemberService;

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
