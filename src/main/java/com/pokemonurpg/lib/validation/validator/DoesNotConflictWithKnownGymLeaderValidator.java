package com.pokemonurpg.lib.validation.validator;

import com.pokemonurpg.lib.service.RequestPathVariableService;
import com.pokemonurpg.lib.validation.annotation.DoesNotConflictWithKnownGymLeader;
import com.pokemonurpg.configuration.v1.champions.KnownChampionService;
import com.pokemonurpg.configuration.v1.elitefourmembers.KnownEliteFourMemberService;
import com.pokemonurpg.configuration.v1.gymleaders.KnownGymLeaderService;
import com.pokemonurpg.entities.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DoesNotConflictWithKnownGymLeaderValidator implements ConstraintValidator<DoesNotConflictWithKnownGymLeader, String> {
    @Resource
    private KnownGymLeaderService knownGymLeaderService;

    @Resource
    private KnownEliteFourMemberService knownEliteFourMemberService;

    @Resource
    private KnownChampionService knownChampionService;

    @Resource
    private MemberService memberService;

    @Resource
    private RequestPathVariableService requestPathVariableService;

    @Override
    public void initialize(DoesNotConflictWithKnownGymLeader constraint) {

    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            Integer dbid = requestPathVariableService.findIntByName("dbid");
            Member member = memberService.findByDbid(dbid);
            return (member != null && input.equalsIgnoreCase(member.getName())) ||
                    (knownGymLeaderService.findByNameExact(input) == null &&
                    knownEliteFourMemberService.findByNameExact(input) == null &&
                    knownChampionService.findByNameExact(input) == null);
        }
        else return true;
    }
}
