package com.pokemonurpg.security.service;

import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorizationCredentialsServiceTest {
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
    private AuthorizationCredentialsService authorizationCredentialsService;

    @Mock
    private RequestHeaderService requestHeaderService;

    @Test
    public void createSession() {
        when(requestHeaderService.findByName("authorization")).thenReturn(HEADER);
        String[] credentials = authorizationCredentialsService.getCredentials();
    }

    @Test
    public void createSessionFailsWhenHeaderIsNull() {
        when(requestHeaderService.findByName("authorization")).thenReturn(null);
        String[] credentials = authorizationCredentialsService.getCredentials();
        assertNull(credentials);
    }

    @Test
    public void createSessionFailsWhenHeaderDoesntStartWithBasic() {
        when(requestHeaderService.findByName("authorization")).thenReturn(HEADER_DOESNT_START_WITH_BASIC);
        String[] credentials = authorizationCredentialsService.getCredentials();
        assertNull(credentials);

    }

    @Test
    public void createSessionFailsWhenCredentialsContainLessThanTwoTokens() {
        when(requestHeaderService.findByName("authorization")).thenReturn(HEADER_LESS_THAN_TWO_TOKENS);
        String[] credentials = authorizationCredentialsService.getCredentials();
        assertNull(credentials);
    }

    @Test
    public void createSessionFailsWhenCredentialsDontContainUserId() {
        when(requestHeaderService.findByName("authorization")).thenReturn(HEADER_NO_DISCORD_ID);
        String[] credentials = authorizationCredentialsService.getCredentials();
        assertNull(credentials);
    }

    @Test
    public void createSessionFailsWhenCredentialsDontContainAccessToken() {
        when(requestHeaderService.findByName("authorization")).thenReturn(HEADER_NO_ACCESS_TOKEN);
        String[] credentials = authorizationCredentialsService.getCredentials();
        assertNull(credentials);
    }

}