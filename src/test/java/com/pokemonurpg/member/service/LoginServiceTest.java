package com.pokemonurpg.member.service;

import com.pokemonurpg.configuration.v1.members.MemberService;
import com.pokemonurpg.login.v1.SessionDto;
import com.pokemonurpg.login.v1.DiscordUserResponse;
import com.pokemonurpg.login.v1.OAuthAccessTokenResponse;
import com.pokemonurpg.login.v1.LoginService;
import com.pokemonurpg.login.v1.OAuthService;
import com.pokemonurpg.login.v1.LoginInputDto;
import com.pokemonurpg.entities.v1.Member;
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
public class LoginServiceTest {
    private final static String CODE = "32436892452";
    private final static OAuthAccessTokenResponse ACCESS_TOKEN_RESPONSE = mock(OAuthAccessTokenResponse.class);
    private final static DiscordUserResponse DISCORD_USER_RESPONSE = mock(DiscordUserResponse.class);
    private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final static String USERNAME = "USERNAME";
    private final static String DISCORD_ID = "DISCORD_ID";

    @InjectMocks
    private LoginService loginService;

    @Mock
    private MemberService memberService;

    @Mock
    private OAuthService oAuthService;

    private LoginInputDto input = new LoginInputDto();
    private Member member = new Member();
    private SessionDto expectedResponse = new SessionDto();

    @Before
    public void init() {
        input.setCode(CODE);

        member.setName(USERNAME);
        member.setDiscordId(DISCORD_ID);

        expectedResponse.setUsername(USERNAME);
        expectedResponse.setId(DISCORD_ID);
        expectedResponse.setAccessToken(ACCESS_TOKEN);
    }

    @Test
    public void login() {
        when(oAuthService.exchangeCodeForAccessToken(CODE)).thenReturn(ACCESS_TOKEN_RESPONSE);
        when(ACCESS_TOKEN_RESPONSE.isValid()).thenReturn(true);
        when(ACCESS_TOKEN_RESPONSE.getAccessToken()).thenReturn(ACCESS_TOKEN);
        when(oAuthService.getDiscordId(ACCESS_TOKEN)).thenReturn(DISCORD_USER_RESPONSE);
        when(DISCORD_USER_RESPONSE.isValid()).thenReturn(true);
        when(DISCORD_USER_RESPONSE.getId()).thenReturn(DISCORD_ID);
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(member);

        SessionDto response = loginService.login(input);

        verify(memberService, times(1)).update(member, ACCESS_TOKEN_RESPONSE);
        assertTrue(EqualsBuilder.reflectionEquals(expectedResponse, response));
    }

    @Test(expected = ResponseStatusException.class)
    public void loginFailsWhenAccessTokenResponseIsNull() {
        when(oAuthService.exchangeCodeForAccessToken(CODE)).thenReturn(null);
        loginService.login(input);
    }

    @Test(expected = ResponseStatusException.class)
    public void loginFailsWhenAccessTokenResponseIsInvalid() {
        when(oAuthService.exchangeCodeForAccessToken(CODE)).thenReturn(ACCESS_TOKEN_RESPONSE);
        when(ACCESS_TOKEN_RESPONSE.isValid()).thenReturn(false);
        loginService.login(input);
    }

    @Test(expected = ResponseStatusException.class)
    public void loginFailsWhenDiscordUserResponseIsNull() {
        when(oAuthService.exchangeCodeForAccessToken(CODE)).thenReturn(ACCESS_TOKEN_RESPONSE);
        when(ACCESS_TOKEN_RESPONSE.isValid()).thenReturn(true);
        when(ACCESS_TOKEN_RESPONSE.getAccessToken()).thenReturn(ACCESS_TOKEN);
        when(oAuthService.getDiscordId(ACCESS_TOKEN)).thenReturn(null);
        loginService.login(input);

    }

    @Test(expected = ResponseStatusException.class)
    public void loginFailsWhenDiscordUserResponseIsInvalid() {
        when(oAuthService.exchangeCodeForAccessToken(CODE)).thenReturn(ACCESS_TOKEN_RESPONSE);
        when(ACCESS_TOKEN_RESPONSE.isValid()).thenReturn(true);
        when(ACCESS_TOKEN_RESPONSE.getAccessToken()).thenReturn(ACCESS_TOKEN);
        when(oAuthService.getDiscordId(ACCESS_TOKEN)).thenReturn(DISCORD_USER_RESPONSE);
        when(DISCORD_USER_RESPONSE.isValid()).thenReturn(false);
        loginService.login(input);
    }

    @Test(expected = ResponseStatusException.class)
    public void loginFailsWhenMemberNotFound() {
        when(oAuthService.exchangeCodeForAccessToken(CODE)).thenReturn(ACCESS_TOKEN_RESPONSE);
        when(ACCESS_TOKEN_RESPONSE.isValid()).thenReturn(true);
        when(ACCESS_TOKEN_RESPONSE.getAccessToken()).thenReturn(ACCESS_TOKEN);
        when(oAuthService.getDiscordId(ACCESS_TOKEN)).thenReturn(DISCORD_USER_RESPONSE);
        when(DISCORD_USER_RESPONSE.isValid()).thenReturn(true);
        when(DISCORD_USER_RESPONSE.getId()).thenReturn(DISCORD_ID);
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(null);
        loginService.login(input);
    }

}