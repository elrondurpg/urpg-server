package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.services.RequestPathVariableService;
import com.pokemonurpg.entities.v1.KnownChampion;
import com.pokemonurpg.entities.v1.KnownEliteFourMember;
import com.pokemonurpg.entities.v1.KnownGymLeader;
import com.pokemonurpg.configuration.v1.champions.KnownChampionService;
import com.pokemonurpg.configuration.v1.elitefourmembers.KnownEliteFourMemberService;
import com.pokemonurpg.configuration.v1.gymleaders.KnownGymLeaderService;
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
public class DoesNotConflictWithKnownGymLeaderValidatorTest {
    private final static Integer    DBID = 432;
    private final static String     NAME = "NAME";

    @InjectMocks
    private DoesNotConflictWithKnownGymLeaderValidator validator;

    @Mock
    private KnownGymLeaderService knownGymLeaderService;

    @Mock
    private KnownEliteFourMemberService knownEliteFourMemberService;

    @Mock
    private KnownChampionService knownChampionService;

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
        when(knownGymLeaderService.findByNameExact(NAME)).thenReturn(null);
        when(knownEliteFourMemberService.findByNameExact(NAME)).thenReturn(null);
        when(knownChampionService.findByNameExact(NAME)).thenReturn(null);
        assertTrue(validator.isValid(NAME, null));
    }

    @Test
    public void isValid_ReturnsFalse_IfAKnownGymLeaderHasThisName() {
        when(knownGymLeaderService.findByNameExact(NAME)).thenReturn(new KnownGymLeader());
        assertFalse(validator.isValid(NAME, null));
    }

    @Test
    public void isValid_ReturnsFalse_IfAKnownEliteFourMemberHasThisName() {
        when(knownGymLeaderService.findByNameExact(NAME)).thenReturn(null);
        when(knownEliteFourMemberService.findByNameExact(NAME)).thenReturn(new KnownEliteFourMember());
        assertFalse(validator.isValid(NAME, null));
    }

    @Test
    public void isValid_ReturnsFalse_IfAKnownChampionHasThisName() {
        when(knownGymLeaderService.findByNameExact(NAME)).thenReturn(null);
        when(knownEliteFourMemberService.findByNameExact(NAME)).thenReturn(null);
        when(knownChampionService.findByNameExact(NAME)).thenReturn(new KnownChampion());
        assertFalse(validator.isValid(NAME, null));
    }

}