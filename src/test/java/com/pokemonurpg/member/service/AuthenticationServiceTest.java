package com.pokemonurpg.member.service;

import com.pokemonurpg.configuration.v1.members.MemberService;
import com.pokemonurpg.login.service.*;
import com.pokemonurpg.entities.Member;
import org.junit.Before;
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
    private final static long SESSION_EXPIRE = 34234L;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private MemberService memberService;

    private Member member = new Member();

    @Mock
    private AccessTokenVerificationService accessTokenVerificationService;

    @Mock
    private SessionExpirationService sessionExpirationService;

    @Before
    public void init() {
        member = new Member();
        member.setSessionExpire(SESSION_EXPIRE);
    }

    @Test
    public void authenticate() {
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(member);
        when(accessTokenVerificationService.verify(member, ACCESS_TOKEN)).thenReturn(true);
        when(sessionExpirationService.isExpired(SESSION_EXPIRE)).thenReturn(false);
        assertEquals(member, authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN));
    }

    @Test
    public void authenticateFailsMemberHasWrongAccessToken() {
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(member);
        when(accessTokenVerificationService.verify(member, ACCESS_TOKEN)).thenReturn(false);
        assertNull(authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN));
    }

    @Test
    public void authenticateFailsSessionExpired() {
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(member);
        when(accessTokenVerificationService.verify(member, ACCESS_TOKEN)).thenReturn(true);
        when(sessionExpirationService.isExpired(SESSION_EXPIRE)).thenReturn(true);

        assertNull(authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN));
    }

    @Test
    public void authenticationFailsWhenMemberNotFound() {
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(null);
        assertNull(authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN));
    }

}