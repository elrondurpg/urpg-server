package com.pokemonurpg.member.service;

import com.pokemonurpg.security.dto.SessionDto;
import com.pokemonurpg.security.models.DiscordUserResponse;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.security.service.LoginService;
import com.pokemonurpg.security.service.OAuthService;
import com.pokemonurpg.security.dto.LoginInputDto;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.configuration.v1.member.member.service.MemberService;

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

    @BeforeEach
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

    @Test
    public void loginFailsWhenAccessTokenResponseIsNull() {
        when(oAuthService.exchangeCodeForAccessToken(CODE)).thenReturn(null);
        assertThrows(ResponseStatusException.class, () -> loginService.login(input));
    }

    @Test
    public void loginFailsWhenAccessTokenResponseIsInvalid() {
        when(oAuthService.exchangeCodeForAccessToken(CODE)).thenReturn(ACCESS_TOKEN_RESPONSE);
        when(ACCESS_TOKEN_RESPONSE.isValid()).thenReturn(false);
        assertThrows(ResponseStatusException.class, () -> loginService.login(input));
    }

    @Test
    public void loginFailsWhenDiscordUserResponseIsNull() {
        when(oAuthService.exchangeCodeForAccessToken(CODE)).thenReturn(ACCESS_TOKEN_RESPONSE);
        when(ACCESS_TOKEN_RESPONSE.isValid()).thenReturn(true);
        when(ACCESS_TOKEN_RESPONSE.getAccessToken()).thenReturn(ACCESS_TOKEN);
        when(oAuthService.getDiscordId(ACCESS_TOKEN)).thenReturn(null);
        assertThrows(ResponseStatusException.class, () -> loginService.login(input));

    }

    @Test
    public void loginFailsWhenDiscordUserResponseIsInvalid() {
        when(oAuthService.exchangeCodeForAccessToken(CODE)).thenReturn(ACCESS_TOKEN_RESPONSE);
        when(ACCESS_TOKEN_RESPONSE.isValid()).thenReturn(true);
        when(ACCESS_TOKEN_RESPONSE.getAccessToken()).thenReturn(ACCESS_TOKEN);
        when(oAuthService.getDiscordId(ACCESS_TOKEN)).thenReturn(DISCORD_USER_RESPONSE);
        when(DISCORD_USER_RESPONSE.isValid()).thenReturn(false);
        assertThrows(ResponseStatusException.class, () -> loginService.login(input));
    }

    @Test
    public void loginFailsWhenMemberNotFound() {
        when(oAuthService.exchangeCodeForAccessToken(CODE)).thenReturn(ACCESS_TOKEN_RESPONSE);
        when(ACCESS_TOKEN_RESPONSE.isValid()).thenReturn(true);
        when(ACCESS_TOKEN_RESPONSE.getAccessToken()).thenReturn(ACCESS_TOKEN);
        when(oAuthService.getDiscordId(ACCESS_TOKEN)).thenReturn(DISCORD_USER_RESPONSE);
        when(DISCORD_USER_RESPONSE.isValid()).thenReturn(true);
        when(DISCORD_USER_RESPONSE.getId()).thenReturn(DISCORD_ID);
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(null);
        assertThrows(ResponseStatusException.class, () -> loginService.login(input));
    }

}