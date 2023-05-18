package com.pokemonurpg.lib.v1.validators;
import com.pokemonurpg.lib.v1.services.RequestPathVariableService;
import com.pokemonurpg.configuration.v1.members.MemberInputDto;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class UniqueDiscordIdValidatorTest {
    private final static Member EXISTING_OBJECT         = new Member();
    private final static int    EXISTING_OBJECT_DBID    = 432;

    private final static int    SOME_OTHER_DBID         = 115;

    private final static String DISCORD_ID              = "DISCORD_ID";

    @InjectMocks
    private UniqueDiscordIdValidator validator;

    @Mock
    private MemberService memberService;

    @Mock
    private RequestPathVariableService requestPathVariableService;

    private MemberInputDto input;

    @Before
    public void init() {
        input = new MemberInputDto();
        input.setDiscordId(DISCORD_ID);

        EXISTING_OBJECT.setDbid(EXISTING_OBJECT_DBID);
    }

    @Test
    public void isValid_ReturnsTrue_WhenInputIsNull() {
        assertTrue(validator.isValid(null, null));
    }

    @Test
    public void isValid_ReturnsTrue_WhenDiscordIdNotProvided() {
        assertTrue(validator.isValid(new MemberInputDto(), null));
    }

    @Test
    public void isValid_ReturnsTrue_WhenDiscordIdIsUnique() {
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(null);
        assertTrue(validator.isValid(input, null));
    }

    @Test
    public void isValid_ReturnsTrue_WhenDiscordIdIsNotUnique_AndRequestDbidMatchesExistingObject() {
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(EXISTING_OBJECT);
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(EXISTING_OBJECT_DBID);
        assertTrue(validator.isValid(input, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenDiscordIdIsNotUnique_AndRequestDbidIsNull() {
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(EXISTING_OBJECT);
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(null);
        assertFalse(validator.isValid(input, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenDiscordIdIsNotUnique_AndRequestDbidDoesNotMatchExistingObject() {
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(EXISTING_OBJECT);
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(SOME_OTHER_DBID);
        assertFalse(validator.isValid(input, null));
    }

}