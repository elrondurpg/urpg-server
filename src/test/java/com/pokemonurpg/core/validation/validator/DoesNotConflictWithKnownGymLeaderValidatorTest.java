package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.core.service.RequestPathVariableService;
import com.pokemonurpg.gym.models.KnownChampion;
import com.pokemonurpg.gym.models.KnownEliteFourMember;
import com.pokemonurpg.gym.models.KnownGymLeader;
import com.pokemonurpg.gym.service.KnownChampionService;
import com.pokemonurpg.gym.service.KnownEliteFourMemberService;
import com.pokemonurpg.gym.service.KnownGymLeaderService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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