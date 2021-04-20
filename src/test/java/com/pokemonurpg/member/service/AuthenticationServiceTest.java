package com.pokemonurpg.member.service;

import com.pokemonurpg.security.service.*;
import com.pokemonurpg.member.models.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {
    private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final static String DISCORD_ID = "DISCORD_ID";

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private MemberService memberService;

    @Mock
    private BotAuthenticationService botAuthenticationService;

    @Mock
    private HumanAuthenticationService humanAuthenticationService;

    private Member member = new Member();

    @Test
    public void authenticateBot() {
        member.setBot(true);
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(member);
        when(botAuthenticationService.authenticate(member, ACCESS_TOKEN)).thenReturn(member);
        assertEquals(member, authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN));
    }

    @Test
    public void authenticateHuman() {
        member.setBot(false);
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(member);
        when(humanAuthenticationService.authenticate(member, ACCESS_TOKEN)).thenReturn(member);
        assertEquals(member, authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN));
    }

    @Test
    public void authenticationFailsWhenMemberNotFound() {
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(null);
        assertNull(authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN));
    }

}