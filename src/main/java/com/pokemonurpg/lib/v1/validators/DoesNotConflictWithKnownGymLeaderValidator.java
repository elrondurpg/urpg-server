package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.services.RequestPathVariableService;
import com.pokemonurpg.lib.v1.annotations.DoesNotConflictWithKnownGymLeader;
import com.pokemonurpg.configuration.v1.champions.ChampionService;
import com.pokemonurpg.configuration.v1.elitefourmembers.EliteFourMemberService;
import com.pokemonurpg.configuration.v1.gymleaders.GymLeaderService;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DoesNotConflictWithKnownGymLeaderValidator implements ConstraintValidator<DoesNotConflictWithKnownGymLeader, String> {
    @Resource
    private GymLeaderService gymLeaderService;

    @Resource
    private EliteFourMemberService eliteFourMemberService;

    @Resource
    private ChampionService championService;

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
                    (gymLeaderService.findByNameExact(input) == null &&
                    eliteFourMemberService.findByNameExact(input) == null &&
                    championService.findByNameExact(input) == null);
        }
        else return true;
    }
}
