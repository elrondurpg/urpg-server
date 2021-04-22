package com.pokemonurpg.security.service;

import com.pokemonurpg.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.member.models.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Base64;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SessionServiceTest {
    private final static String DISCORD_ID = "DISCORD_ID";
    private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final static String[] CREDENTIALS = { DISCORD_ID, ACCESS_TOKEN };

    @InjectMocks
    private SessionService sessionService;

    @Mock
    private AuthorizationCredentialsService authorizationCredentialsService;

    @Mock
    private AuthenticationService authenticationService;

    @Test
    public void createSession() {
        Member member = new Member();
        when(authorizationCredentialsService.getCredentials()).thenReturn(CREDENTIALS);
        when(authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN)).thenReturn(member);
        sessionService.createSession();
        assertEquals(member, sessionService.getAuthenticatedMember());
        assertEquals(ACCESS_TOKEN, sessionService.getAccessToken());
    }

    @Test
    public void createSessionFailsWhenCredentialsAreNull() {
        when(authorizationCredentialsService.getCredentials()).thenReturn(null);
        sessionService.createSession();
        assertNull(sessionService.getAuthenticatedMember());
        assertNull(sessionService.getAccessToken());
    }

    @Test
    public void createSessionFailsWhenMemberIsNotAuthenticated() {
        when(authorizationCredentialsService.getCredentials()).thenReturn(CREDENTIALS);
        when(authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN)).thenReturn(null);
        sessionService.createSession();
        assertNull(sessionService.getAuthenticatedMember());
        assertNull(sessionService.getAccessToken());
    }


}