package com.pokemonurpg.security.service;

import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.configuration.v1.member.member.service.MemberService;
import com.pokemonurpg.security.dto.SessionDto;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BotLoginServiceTest {
    private final static String DISCORD_ID = "DISCORD_ID";
    private final static String SECRET = "SECRET";
    private final static String[] CREDENTIALS = { DISCORD_ID, SECRET };
    private final static OAuthAccessTokenResponse ACCESS_TOKEN_RESPONSE = mock(OAuthAccessTokenResponse.class);
    private static final String USERNAME = "USERNAME";
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";

    @InjectMocks
    private BotLoginService botLoginService;

    @Mock
    private AuthorizationCredentialsService authorizationCredentialsService;

    @Mock
    private OAuthService oAuthService;

    @Mock
    private MemberService memberService;

    private Member member;

    private SessionDto expectedResponse;

    @BeforeEach
    public void init() {
        member = new Member();
        member.setName(USERNAME);
        member.setDiscordId(DISCORD_ID);
        member.setBot(true);

        expectedResponse = new SessionDto();
        expectedResponse.setUsername(USERNAME);
        expectedResponse.setId(DISCORD_ID);
        expectedResponse.setAccessToken(ACCESS_TOKEN);
    }

    @Test
    public void login() {

        when(authorizationCredentialsService.getCredentials()).thenReturn(CREDENTIALS);
        when(oAuthService.getAccessTokenForClientCredentials(DISCORD_ID, SECRET)).thenReturn(ACCESS_TOKEN_RESPONSE);
        when(ACCESS_TOKEN_RESPONSE.isValid()).thenReturn(true);
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(member);
        when(ACCESS_TOKEN_RESPONSE.getAccessToken()).thenReturn(ACCESS_TOKEN);

        SessionDto response = botLoginService.login();

        verify(memberService, times(1)).update(member, ACCESS_TOKEN_RESPONSE);
        assertTrue(EqualsBuilder.reflectionEquals(expectedResponse, response));
    }

    @Test
    public void loginFailsWhenCredentialsAreNull() {
        when(authorizationCredentialsService.getCredentials()).thenReturn(null);
        assertThrows(ResponseStatusException.class, () -> botLoginService.login());
    }

    @Test
    public void loginFailsWhenCredentialsAreWrongSize() {
        when(authorizationCredentialsService.getCredentials()).thenReturn(new String[] { });
        assertThrows(ResponseStatusException.class, () -> botLoginService.login());
    }

    @Test
    public void loginFailsWhenAccessTokenResponseIsNull() {
        when(authorizationCredentialsService.getCredentials()).thenReturn(CREDENTIALS);
        when(oAuthService.getAccessTokenForClientCredentials(DISCORD_ID, SECRET)).thenReturn(null);
        assertThrows(ResponseStatusException.class, () -> botLoginService.login());
    }

    @Test
    public void loginFailsWhenAccessTokenResponseIsInvalid() {
        when(authorizationCredentialsService.getCredentials()).thenReturn(CREDENTIALS);
        when(oAuthService.getAccessTokenForClientCredentials(DISCORD_ID, SECRET)).thenReturn(ACCESS_TOKEN_RESPONSE);
        when(ACCESS_TOKEN_RESPONSE.isValid()).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> botLoginService.login());
    }

    @Test
    public void loginFailsWhenMemberNotFound() {
        when(authorizationCredentialsService.getCredentials()).thenReturn(CREDENTIALS);
        when(oAuthService.getAccessTokenForClientCredentials(DISCORD_ID, SECRET)).thenReturn(ACCESS_TOKEN_RESPONSE);
        when(ACCESS_TOKEN_RESPONSE.isValid()).thenReturn(true);
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(null);

        assertThrows(ResponseStatusException.class, () -> botLoginService.login());
    }

    @Test
    public void loginFailsWhenMemberNotBot() {
        member.setBot(false);

        when(authorizationCredentialsService.getCredentials()).thenReturn(CREDENTIALS);
        when(oAuthService.getAccessTokenForClientCredentials(DISCORD_ID, SECRET)).thenReturn(ACCESS_TOKEN_RESPONSE);
        when(ACCESS_TOKEN_RESPONSE.isValid()).thenReturn(true);
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(member);

        assertThrows(ResponseStatusException.class, () -> { SessionDto response = botLoginService.login(); });
    }

}