package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.services.RequestPathVariableService;
import com.pokemonurpg.entities.v1.Champion;
import com.pokemonurpg.entities.v1.EliteFourMember;
import com.pokemonurpg.entities.v1.GymLeader;
import com.pokemonurpg.configuration.v1.champions.ChampionService;
import com.pokemonurpg.configuration.v1.elitefourmembers.EliteFourMemberService;
import com.pokemonurpg.configuration.v1.gymleaders.GymLeaderService;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DoesNotConflictWithGymLeaderValidatorTest {
    private final static Integer    DBID = 432;
    private final static String     NAME = "NAME";

    @InjectMocks
    private DoesNotConflictWithKnownGymLeaderValidator validator;

    @Mock
    private GymLeaderService gymLeaderService;

    @Mock
    private EliteFourMemberService eliteFourMemberService;

    @Mock
    private ChampionService championService;

    @Mock
    private MemberService memberService;

    @Mock
    private RequestPathVariableService requestPathVariableService;

    @Test
    public void initialize() {
        validator.initialize(null);
    }

    @Test
    public void isValid_ReturnsTrue_IfInputIsNull() {
        assertTrue(validator.isValid(null, null));
    }

    @Test
    public void isValid_ReturnsTrue_IfThisMemberAlreadyHasThisName() {
        Member member = new Member();
        member.setName(NAME);

        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(memberService.findByDbid(DBID)).thenReturn(member);
        assertTrue(validator.isValid(NAME.toLowerCase(), null));
    }

    @Test
    public void isValid_ReturnsTrue_IfNoKnownGymLeaderEliteFourMemberOrChampionHasThisName() {
        when(gymLeaderService.findByNameExact(NAME)).thenReturn(null);
        when(eliteFourMemberService.findByNameExact(NAME)).thenReturn(null);
        when(championService.findByNameExact(NAME)).thenReturn(null);
        assertTrue(validator.isValid(NAME, null));
    }

    @Test
    public void isValid_ReturnsFalse_IfAKnownGymLeaderHasThisName() {
        when(gymLeaderService.findByNameExact(NAME)).thenReturn(new GymLeader());
        assertFalse(validator.isValid(NAME, null));
    }

    @Test
    public void isValid_ReturnsFalse_IfAKnownEliteFourMemberHasThisName() {
        when(gymLeaderService.findByNameExact(NAME)).thenReturn(null);
        when(eliteFourMemberService.findByNameExact(NAME)).thenReturn(new EliteFourMember());
        assertFalse(validator.isValid(NAME, null));
    }

    @Test
    public void isValid_ReturnsFalse_IfAKnownChampionHasThisName() {
        when(gymLeaderService.findByNameExact(NAME)).thenReturn(null);
        when(eliteFourMemberService.findByNameExact(NAME)).thenReturn(null);
        when(championService.findByNameExact(NAME)).thenReturn(new Champion());
        assertFalse(validator.isValid(NAME, null));
    }

}