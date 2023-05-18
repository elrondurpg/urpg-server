package com.pokemonurpg.login.v1;

import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
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

    private Session expectedResponse;

    @Before
    public void init() {
        member = new Member();
        member.setName(USERNAME);
        member.setDiscordId(DISCORD_ID);
        member.setBot(true);

        expectedResponse = new Session();
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

        Session response = botLoginService.login();

        verify(memberService, times(1)).update(member, ACCESS_TOKEN_RESPONSE);
        assertTrue(EqualsBuilder.reflectionEquals(expectedResponse, response));
    }

    @Test(expected = ResponseStatusException.class)
    public void loginFailsWhenCredentialsAreNull() {
        when(authorizationCredentialsService.getCredentials()).thenReturn(null);
        botLoginService.login();
    }

    @Test(expected = ResponseStatusException.class)
    public void loginFailsWhenCredentialsAreWrongSize() {
        when(authorizationCredentialsService.getCredentials()).thenReturn(new String[] { });
        botLoginService.login();
    }

    @Test(expected = ResponseStatusException.class)
    public void loginFailsWhenAccessTokenResponseIsNull() {
        when(authorizationCredentialsService.getCredentials()).thenReturn(CREDENTIALS);
        when(oAuthService.getAccessTokenForClientCredentials(DISCORD_ID, SECRET)).thenReturn(null);
        botLoginService.login();
    }

    @Test(expected = ResponseStatusException.class)
    public void loginFailsWhenAccessTokenResponseIsInvalid() {
        when(authorizationCredentialsService.getCredentials()).thenReturn(CREDENTIALS);
        when(oAuthService.getAccessTokenForClientCredentials(DISCORD_ID, SECRET)).thenReturn(ACCESS_TOKEN_RESPONSE);
        when(ACCESS_TOKEN_RESPONSE.isValid()).thenReturn(false);

        botLoginService.login();
    }

    @Test(expected = ResponseStatusException.class)
    public void loginFailsWhenMemberNotFound() {
        when(authorizationCredentialsService.getCredentials()).thenReturn(CREDENTIALS);
        when(oAuthService.getAccessTokenForClientCredentials(DISCORD_ID, SECRET)).thenReturn(ACCESS_TOKEN_RESPONSE);
        when(ACCESS_TOKEN_RESPONSE.isValid()).thenReturn(true);
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(null);

        botLoginService.login();
    }

    @Test(expected = ResponseStatusException.class)
    public void loginFailsWhenMemberNotBot() {
        member.setBot(false);

        when(authorizationCredentialsService.getCredentials()).thenReturn(CREDENTIALS);
        when(oAuthService.getAccessTokenForClientCredentials(DISCORD_ID, SECRET)).thenReturn(ACCESS_TOKEN_RESPONSE);
        when(ACCESS_TOKEN_RESPONSE.isValid()).thenReturn(true);
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(member);

        Session response = botLoginService.login();
    }

}