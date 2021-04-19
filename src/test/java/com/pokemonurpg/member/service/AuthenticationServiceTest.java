package com.pokemonurpg.member.service;

import com.pokemonurpg.core.security.dto.SessionDto;
import com.pokemonurpg.core.security.models.DiscordUserResponse;
import com.pokemonurpg.core.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.core.security.service.AesEncryptionService;
import com.pokemonurpg.core.security.service.HashService;
import com.pokemonurpg.core.security.service.OAuthService;
import com.pokemonurpg.core.security.service.SessionService;
import com.pokemonurpg.core.service.SystemService;
import com.pokemonurpg.member.input.LoginInputDto;
import com.pokemonurpg.member.models.Member;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {
    private final static String CODE = "CODE";
    private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final static String HASHED_ACCESS_TOKEN = "4211131";
    private final static String BAD_ACCESS_TOKEN = "BAD_ACCESS_TOKEN";
    private final static String HASHED_BAD_ACCESS_TOKEN = "25y54yh45j";
    private final static String USERNAME = "USERNAME";
    private final static String DISCORD_ID = "DISCORD_ID";
    private final static Long CURRENT_TIME_MILLIS = 52342000L;
    private final static Integer SALT = 32847242;
    private final static Member MEMBER = new Member();
    private final static String REFRESH_TOKEN = "REFRESH_TOKEN";
    private final static OAuthAccessTokenResponse REFRESH_TOKEN_RESPONSE = mock(OAuthAccessTokenResponse.class);
    private final static byte[] IV = {};
    private final static SecretKey SECRET_KEY = mock(SecretKey.class);
    private final static String DECRYPTED_REFRESH_TOKEN = "DECRYPTED_REFRESH_TOKEN";

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private OAuthService oAuthService;

    @Mock
    private MemberService memberService;

    @Mock
    private SessionService sessionService;

    @Mock
    private SystemService systemService;

    @Mock
    private HashService hashService;

    @Mock
    private AesEncryptionService aesEncryptionService;

    @Test
    public void successfulLogin() throws NoSuchAlgorithmException {
        // Given a LoginInputDto with code = CODE
        LoginInputDto input = new LoginInputDto();
        input.setCode(CODE);

        // Given oAuthService.exchangeCodeForAccessToken(code) returns a valid OAuthAccessTokenResponse
        OAuthAccessTokenResponse accessTokenResponse = mock(OAuthAccessTokenResponse.class);
        when(accessTokenResponse.getAccessToken()).thenReturn(ACCESS_TOKEN);
        when(accessTokenResponse.isValid()).thenReturn(true);
        when(oAuthService.exchangeCodeForAccessToken(CODE)).thenReturn(accessTokenResponse);

        // Given oAuthService.getDiscordId(accessTokenResponse.getAccessToken()) returns a valid DiscordUserResponse
        DiscordUserResponse discordUserResponse = mock(DiscordUserResponse.class);
        when(discordUserResponse.getId()).thenReturn(DISCORD_ID);
        when(discordUserResponse.isValid()).thenReturn(true);
        when(oAuthService.getDiscordId(ACCESS_TOKEN)).thenReturn(discordUserResponse);

        // Given memberService.findByDiscordId(id) returns a MEMBER
        Member member = mock(Member.class);
        when(member.getUsername()).thenReturn(USERNAME);
        when(member.getDiscordId()).thenReturn(DISCORD_ID);
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(member);

        // When I call authenticationService.login(CODE)
        // Then I will receive a sessionDto
        SessionDto expected = new SessionDto(USERNAME, DISCORD_ID, ACCESS_TOKEN);
        SessionDto actual = authenticationService.login(input);
        assertTrue(EqualsBuilder.reflectionEquals(expected, actual));

        // and the MEMBER object will be updated
        verify(memberService, times(1)).update(member, accessTokenResponse);
    }

    @Test(expected = ResponseStatusException.class)
    public void failWhenAccessTokenResponseIsInvalid() {
        // Given a LoginInputDto with code = CODE
        LoginInputDto input = new LoginInputDto();
        input.setCode(CODE);

        // Given oAuthService.exchangeCodeForAccessToken(code) returns an invalid OAuthAccessTokenResponse
        OAuthAccessTokenResponse accessTokenResponse = mock(OAuthAccessTokenResponse.class);
        when(accessTokenResponse.isValid()).thenReturn(false);
        when(oAuthService.exchangeCodeForAccessToken(CODE)).thenReturn(accessTokenResponse);

        authenticationService.login(input);
    }

    @Test(expected = ResponseStatusException.class)
    public void failWhenDiscordUserResponseIsInvalid() {
        // Given a LoginInputDto with code = CODE
        LoginInputDto input = new LoginInputDto();
        input.setCode(CODE);

        // Given oAuthService.exchangeCodeForAccessToken(code) returns a valid OAuthAccessTokenResponse
        OAuthAccessTokenResponse accessTokenResponse = mock(OAuthAccessTokenResponse.class);
        when(accessTokenResponse.getAccessToken()).thenReturn(ACCESS_TOKEN);
        when(accessTokenResponse.isValid()).thenReturn(true);
        when(oAuthService.exchangeCodeForAccessToken(CODE)).thenReturn(accessTokenResponse);

        // Given oAuthService.getDiscordId(accessTokenResponse.getAccessToken()) returns an invalid DiscordUserResponse
        DiscordUserResponse discordUserResponse = mock(DiscordUserResponse.class);
        when(discordUserResponse.isValid()).thenReturn(false);
        when(oAuthService.getDiscordId(ACCESS_TOKEN)).thenReturn(discordUserResponse);

        authenticationService.login(input);
    }

    @Test(expected = ResponseStatusException.class)
    public void failWhenMemberIsInvalid() {
        // Given a LoginInputDto with code = CODE
        LoginInputDto input = new LoginInputDto();
        input.setCode(CODE);

        // Given oAuthService.exchangeCodeForAccessToken(code) returns a valid OAuthAccessTokenResponse
        OAuthAccessTokenResponse accessTokenResponse = mock(OAuthAccessTokenResponse.class);
        when(accessTokenResponse.getAccessToken()).thenReturn(ACCESS_TOKEN);
        when(accessTokenResponse.isValid()).thenReturn(true);
        when(oAuthService.exchangeCodeForAccessToken(CODE)).thenReturn(accessTokenResponse);

        // Given oAuthService.getDiscordId(accessTokenResponse.getAccessToken()) returns a valid DiscordUserResponse
        DiscordUserResponse discordUserResponse = mock(DiscordUserResponse.class);
        when(discordUserResponse.getId()).thenReturn(DISCORD_ID);
        when(discordUserResponse.isValid()).thenReturn(true);
        when(oAuthService.getDiscordId(ACCESS_TOKEN)).thenReturn(discordUserResponse);

        // Given memberService.findByDiscordId(id) returns null
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(null);

        authenticationService.login(input);
    }

    @Test
    public void successfulRefresh() {
        SessionDto session = new SessionDtoTestBuilder()
                .withFoundDiscordId()
                .withGoodAccessToken()
                .withUnexpiredSession()
                .withRefreshTokenAndIv()
                .withValidRefreshTokenResponse()
                .whereMemberHasAccessToken()
                .build();

        when(aesEncryptionService.getKeyFromAccessToken(ACCESS_TOKEN, SALT)).thenReturn(SECRET_KEY);
        when(aesEncryptionService.decrypt(eq(REFRESH_TOKEN), eq(SECRET_KEY), any(IvParameterSpec.class))).thenReturn(DECRYPTED_REFRESH_TOKEN);

        SessionDto expected = new SessionDto(USERNAME, DISCORD_ID, ACCESS_TOKEN);
        SessionDto actual = authenticationService.refresh(session);
        assertTrue(EqualsBuilder.reflectionEquals(expected, actual));

        verify(memberService, times(1)).update(MEMBER, REFRESH_TOKEN_RESPONSE);
    }

    @Test
    public void refreshFailsWhenMemberNotFound() {
        SessionDto session = new SessionDto();
        assertNull(authenticationService.refresh(session));
        verify(memberService, times(0)).update(Matchers.any(), Matchers.any());
    }

    @Test
    public void refreshFailsWhenRefreshTokenResponseIsInvalid() {
        SessionDto session = new SessionDtoTestBuilder()
                .withFoundDiscordId()
                .withGoodAccessToken()
                .withUnexpiredSession()
                .withInvalidRefreshTokenResponse()
                .whereMemberHasAccessToken()
                .build();

        assertNull(authenticationService.refresh(session));
        verify(memberService, times(0)).update(Matchers.any(), Matchers.any());

    }

    @Test
    public void successfulAuthentication() {
        SessionDto session = new SessionDtoTestBuilder()
            .withFoundDiscordId()
            .withGoodAccessToken()
            .withUnexpiredSession()
            .whereMemberHasAccessToken()
            .build();

        assertEquals(MEMBER, authenticationService.authenticate(session));}

    @Test
    public void authenticationFailsWhenMemberNotFound() {
        SessionDto session = new SessionDto();
        assertNull(authenticationService.authenticate(session));
    }

    @Test
    public void authenticationFailsWhenMemberHasNullAccessToken() {
        SessionDto session = new SessionDtoTestBuilder()
                .withFoundDiscordId()
                .withGoodAccessToken()
                .withUnexpiredSession()
                .whereMemberHasNullAccessToken()
                .build();
        assertNull(authenticationService.authenticate(session));
    }

    @Test
    public void authenticationFailsWhenMemberHasEmptyAccessToken() {
        SessionDto session = new SessionDtoTestBuilder()
                .withFoundDiscordId()
                .withGoodAccessToken()
                .withUnexpiredSession()
                .whereMemberHasEmptyAccessToken()
                .build();
        assertNull(authenticationService.authenticate(session));
    }

    @Test
    public void authenticationFailsWhenAccessTokenDoesntMatch() {
        SessionDto session = new SessionDtoTestBuilder()
                .withFoundDiscordId()
                .withBadAccessToken()
                .withUnexpiredSession()
                .whereMemberHasAccessToken()
                .build();
        assertNull(authenticationService.authenticate(session));
    }

    @Test
    public void authenticationFailsWhenHashServiceThrowsError() {
        SessionDto session = new SessionDtoTestBuilder()
                .withFoundDiscordId()
                .withGoodAccessToken()
                .withUnexpiredSession()
                .whereMemberHasAccessToken()
                .withHashException()
                .build();
        assertNull(authenticationService.authenticate(session));
    }

    @Test
    public void authenticationFailsWhenSessionIsExpired() {
        SessionDto session = new SessionDtoTestBuilder()
                .withFoundDiscordId()
                .withGoodAccessToken()
                .withExpiredSession()
                .whereMemberHasAccessToken()
                .build();

        assertNull(authenticationService.authenticate(session));
    }

    public class SessionDtoTestBuilder {
        private SessionDto session = new SessionDto();

        SessionDtoTestBuilder withRefreshTokenAndIv() {
            MEMBER.setRefreshToken(REFRESH_TOKEN);
            MEMBER.setRefreshTokenIv(IV);
            return this;
        }

        SessionDtoTestBuilder whereMemberHasAccessToken() {
            MEMBER.setAccessToken(HASHED_ACCESS_TOKEN);
            MEMBER.setSalt(SALT);
            return this;
        }

        SessionDtoTestBuilder whereMemberHasEmptyAccessToken() {
            MEMBER.setAccessToken("");
            MEMBER.setSalt(SALT);
            return this;
        }

        SessionDtoTestBuilder whereMemberHasNullAccessToken() {
            MEMBER.setSalt(SALT);
            return this;
        }

        SessionDtoTestBuilder withGoodAccessToken() {
            session.setAccessToken(ACCESS_TOKEN);
            when(hashService.hash(ACCESS_TOKEN + SALT)).thenReturn(HASHED_ACCESS_TOKEN);
            return this;
        }

        SessionDtoTestBuilder withBadAccessToken() {
            session.setAccessToken(BAD_ACCESS_TOKEN);
            when(hashService.hash(BAD_ACCESS_TOKEN + SALT)).thenReturn(HASHED_BAD_ACCESS_TOKEN);
            return this;
        }

        private SessionDtoTestBuilder withSessionMatters() {
            when(systemService.currentTimeMillis()).thenReturn(CURRENT_TIME_MILLIS);
            return this;
        }

        SessionDtoTestBuilder withExpiredSession() {
            MEMBER.setSessionExpire(CURRENT_TIME_MILLIS / 1000 + 59);
            return withSessionMatters();
        }

        SessionDtoTestBuilder withUnexpiredSession() {
            MEMBER.setSessionExpire(CURRENT_TIME_MILLIS / 1000 + 61);
            return withSessionMatters();
        }

        SessionDtoTestBuilder withHashException() {
            when(hashService.hash(Matchers.any())).thenThrow(ResponseStatusException.class);
            return this;
        }

        SessionDtoTestBuilder withFoundDiscordId() {
            MEMBER.setUsername(USERNAME);
            MEMBER.setDiscordId(DISCORD_ID);
            session.setId(DISCORD_ID);
            when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(MEMBER);
            return this;
        }

        private SessionDtoTestBuilder withRefreshTokenMatters() {
            when(oAuthService.refreshAccessToken(DECRYPTED_REFRESH_TOKEN)).thenReturn(REFRESH_TOKEN_RESPONSE);
            return this;
        }

        SessionDtoTestBuilder withValidRefreshTokenResponse() {
            when(REFRESH_TOKEN_RESPONSE.isValid()).thenReturn(true);
            return withRefreshTokenMatters();
        }

        SessionDtoTestBuilder withInvalidRefreshTokenResponse() {
            when(REFRESH_TOKEN_RESPONSE.isValid()).thenReturn(false);
            return withRefreshTokenMatters();
        }

        SessionDto build() {
            return session;
        }

    }

}