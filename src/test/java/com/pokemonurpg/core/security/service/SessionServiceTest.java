package com.pokemonurpg.core.security.service;

import com.pokemonurpg.core.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.core.service.RequestHeaderService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.AuthenticationService;
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
    private final static OAuthAccessTokenResponse ACCESS_TOKEN_RESPONSE = mock(OAuthAccessTokenResponse.class);
    private final static String USERNAME = "USERNAME";

    private final static String DISCORD_ID = "DISCORD_ID";
    private final static String ACCESS_TOKEN = "ACCESS_TOKEN";

    private final static String HEADER = "Basic " + Base64.getEncoder().encodeToString((DISCORD_ID + ":" + ACCESS_TOKEN).getBytes());
    private final static String HEADER_DOESNT_START_WITH_BASIC = Base64.getEncoder().encodeToString((DISCORD_ID + ":" + ACCESS_TOKEN).getBytes());
    private final static String HEADER_LESS_THAN_TWO_TOKENS = "Basic " + Base64.getEncoder().encodeToString(("TEST").getBytes());
    private final static String HEADER_NO_DISCORD_ID = "Basic " + Base64.getEncoder().encodeToString((":" + ACCESS_TOKEN).getBytes());
    private final static String HEADER_NO_ACCESS_TOKEN = "Basic " + Base64.getEncoder().encodeToString((DISCORD_ID + ":").getBytes());

    @InjectMocks
    private SessionService sessionService;

    @Mock
    private RequestHeaderService requestHeaderService;

    @Mock
    private AuthenticationService authenticationService;

    @Test
    public void createSession() {
        Member member = new Member();
        when(requestHeaderService.findByName("authorization")).thenReturn(HEADER);
        when(authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN)).thenReturn(member);
        sessionService.createSession();
        assertEquals(member, sessionService.getAuthenticatedMember());
        assertEquals(ACCESS_TOKEN, sessionService.getAccessToken());
    }

    @Test
    public void createSessionFailsWhenHeaderIsNull() {
        when(requestHeaderService.findByName("authorization")).thenReturn(null);
        sessionService.createSession();
        assertNull(sessionService.getAuthenticatedMember());
        assertNull(sessionService.getAccessToken());
    }

    @Test
    public void createSessionFailsWhenHeaderDoesntStartWithBasic() {
        when(requestHeaderService.findByName("authorization")).thenReturn(HEADER_DOESNT_START_WITH_BASIC);
        sessionService.createSession();
        assertNull(sessionService.getAuthenticatedMember());
        assertNull(sessionService.getAccessToken());

    }

    @Test
    public void createSessionFailsWhenCredentialsContainLessThanTwoTokens() {
        when(requestHeaderService.findByName("authorization")).thenReturn(HEADER_LESS_THAN_TWO_TOKENS);
        sessionService.createSession();
        assertNull(sessionService.getAuthenticatedMember());
        assertNull(sessionService.getAccessToken());
    }

    @Test
    public void createSessionFailsWhenCredentialsDontContainUserId() {
        when(requestHeaderService.findByName("authorization")).thenReturn(HEADER_NO_DISCORD_ID);
        sessionService.createSession();
        assertNull(sessionService.getAuthenticatedMember());
        assertNull(sessionService.getAccessToken());
    }

    @Test
    public void createSessionFailsWhenCredentialsDontContainAccessToken() {
        when(requestHeaderService.findByName("authorization")).thenReturn(HEADER_NO_ACCESS_TOKEN);
        sessionService.createSession();
        assertNull(sessionService.getAuthenticatedMember());
        assertNull(sessionService.getAccessToken());
    }

    @Test
    public void createSessionFailsWhenNoMemberIsAuthenticated() {
        when(requestHeaderService.findByName("authorization")).thenReturn(HEADER);
        when(authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN)).thenReturn(null);
        sessionService.createSession();
        assertNull(sessionService.getAuthenticatedMember());
        assertNull(sessionService.getAccessToken());
    }
}