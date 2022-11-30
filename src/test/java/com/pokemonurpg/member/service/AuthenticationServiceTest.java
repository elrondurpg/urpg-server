package com.pokemonurpg.member.service;

import com.pokemonurpg.security.service.*;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {/*
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

    @BeforeEach
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
    }*/

}