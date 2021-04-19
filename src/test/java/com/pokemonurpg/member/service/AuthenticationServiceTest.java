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
import javax.inject.Provider;
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

    @Mock
    private Provider<SessionService> sessionServiceProvider;

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
    public void failLoginWhenAccessTokenResponseIsInvalid() {
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
    public void failLoginWhenDiscordUserResponseIsInvalid() {
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
    public void failLoginWhenMemberIsInvalid() {
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
        SessionService sessionService = mock(SessionService.class);
        when(sessionServiceProvider.get()).thenReturn(sessionService);

        Member member = new TestMemberBuilder()
                .withFoundDiscordId()
                .withUnexpiredSession()
                .withRefreshTokenAndIv()
                .withValidRefreshTokenResponse()
                .build();

        when(sessionService.getAuthenticatedMember()).thenReturn(member);
        when(sessionService.getAccessToken()).thenReturn(ACCESS_TOKEN);

        when(aesEncryptionService.getKeyFromAccessToken(ACCESS_TOKEN, SALT)).thenReturn(SECRET_KEY);
        when(aesEncryptionService.decrypt(eq(REFRESH_TOKEN), eq(SECRET_KEY), any(IvParameterSpec.class))).thenReturn(DECRYPTED_REFRESH_TOKEN);

        SessionDto expected = new SessionDto(USERNAME, DISCORD_ID, ACCESS_TOKEN);
        SessionDto actual = authenticationService.refresh();
        assertTrue(EqualsBuilder.reflectionEquals(expected, actual));

        verify(memberService, times(1)).update(member, REFRESH_TOKEN_RESPONSE);
    }

    @Test
    public void refreshFailsWhenMemberNotFound() {
        SessionService sessionService = mock(SessionService.class);
        when(sessionServiceProvider.get()).thenReturn(sessionService);
        when(sessionService.getAuthenticatedMember()).thenReturn(null);

        assertNull(authenticationService.refresh());
        verify(memberService, times(0)).update(Matchers.any(), Matchers.any());
    }

    @Test
    public void refreshFailsWhenRefreshTokenResponseIsInvalid() {
        SessionService sessionService = mock(SessionService.class);
        when(sessionServiceProvider.get()).thenReturn(sessionService);

        Member member = new TestMemberBuilder()
                .withFoundDiscordId()
                .withUnexpiredSession()
                .withRefreshTokenAndIv()
                .withInvalidRefreshTokenResponse()
                .build();

        when(sessionService.getAuthenticatedMember()).thenReturn(member);
        when(sessionService.getAccessToken()).thenReturn(ACCESS_TOKEN);

        when(aesEncryptionService.getKeyFromAccessToken(ACCESS_TOKEN, SALT)).thenReturn(SECRET_KEY);
        when(aesEncryptionService.decrypt(eq(REFRESH_TOKEN), eq(SECRET_KEY), any(IvParameterSpec.class))).thenReturn(DECRYPTED_REFRESH_TOKEN);

        assertNull(authenticationService.refresh());
        verify(memberService, times(0)).update(Matchers.any(), Matchers.any());

    }

    @Test
    public void successfulAuthentication() {
        when(hashService.hash(ACCESS_TOKEN + SALT)).thenReturn(HASHED_ACCESS_TOKEN);

        Member member = new TestMemberBuilder()
                .withFoundDiscordId()
                .withUnexpiredSession()
                .withRefreshTokenAndIv()
                .whereMemberHasAccessToken()
                .build();

        assertEquals(member, authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN));
    }

    @Test
    public void authenticationFailsWhenMemberNotFound() {
        assertNull(authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN));
    }

    @Test
    public void authenticationFailsWhenMemberHasNullAccessToken() {
        Member member = new TestMemberBuilder()
                .withFoundDiscordId()
                .withUnexpiredSession()
                .withRefreshTokenAndIv()
                .whereMemberHasNullAccessToken()
                .build();

        assertNull(authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN));
    }

    @Test
    public void authenticationFailsWhenMemberHasEmptyAccessToken() {
        Member member = new TestMemberBuilder()
                .withFoundDiscordId()
                .withUnexpiredSession()
                .withRefreshTokenAndIv()
                .whereMemberHasEmptyAccessToken()
                .build();

        assertNull(authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN));
    }

    @Test
    public void authenticationFailsWhenAccessTokenDoesntMatch() {
        when(hashService.hash(BAD_ACCESS_TOKEN + SALT)).thenReturn(HASHED_BAD_ACCESS_TOKEN);

        Member member = new TestMemberBuilder()
                .withFoundDiscordId()
                .withUnexpiredSession()
                .withRefreshTokenAndIv()
                .whereMemberHasAccessToken()
                .build();

        assertNull(authenticationService.authenticate(DISCORD_ID, BAD_ACCESS_TOKEN));
    }

    @Test
    public void authenticationFailsWhenHashServiceThrowsError() {
        Member member = new TestMemberBuilder()
                .withFoundDiscordId()
                .withUnexpiredSession()
                .withRefreshTokenAndIv()
                .whereMemberHasAccessToken()
                .withHashException()
                .build();
        assertNull(authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN));
    }

    @Test
    public void authenticationFailsWhenSessionIsExpired() {
        Member member = new TestMemberBuilder()
                .withFoundDiscordId()
                .withExpiredSession()
                .withRefreshTokenAndIv()
                .whereMemberHasAccessToken()
                .build();

        assertNull(authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN));
    }

    public class TestMemberBuilder {
        private Member member = new Member();

        TestMemberBuilder withRefreshTokenAndIv() {
            member.setRefreshToken(REFRESH_TOKEN);
            member.setRefreshTokenIv(IV);
            return this;
        }

        TestMemberBuilder whereMemberHasAccessToken() {
            member.setAccessToken(HASHED_ACCESS_TOKEN);
            member.setSalt(SALT);
            return this;
        }

        TestMemberBuilder whereMemberHasEmptyAccessToken() {
            member.setAccessToken("");
            member.setSalt(SALT);
            return this;
        }

        TestMemberBuilder whereMemberHasNullAccessToken() {
            member.setSalt(SALT);
            return this;
        }

        private TestMemberBuilder withSessionMatters() {
            when(systemService.currentTimeMillis()).thenReturn(CURRENT_TIME_MILLIS);
            return this;
        }

        TestMemberBuilder withExpiredSession() {
            member.setSessionExpire(CURRENT_TIME_MILLIS / 1000 + 59);
            return withSessionMatters();
        }

        TestMemberBuilder withUnexpiredSession() {
            member.setSessionExpire(CURRENT_TIME_MILLIS / 1000 + 61);
            return withSessionMatters();
        }

        TestMemberBuilder withFoundDiscordId() {
            member.setUsername(USERNAME);
            member.setDiscordId(DISCORD_ID);
            when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(member);
            return this;
        }

        TestMemberBuilder withHashException() {
            when(hashService.hash(Matchers.any())).thenThrow(ResponseStatusException.class);
            return this;
        }

        private TestMemberBuilder withRefreshTokenMatters() {
            when(oAuthService.refreshAccessToken(DECRYPTED_REFRESH_TOKEN)).thenReturn(REFRESH_TOKEN_RESPONSE);
            return this;
        }

        TestMemberBuilder withValidRefreshTokenResponse() {
            when(REFRESH_TOKEN_RESPONSE.getAccessToken()).thenReturn(ACCESS_TOKEN);
            when(REFRESH_TOKEN_RESPONSE.isValid()).thenReturn(true);
            return withRefreshTokenMatters();
        }

        TestMemberBuilder withInvalidRefreshTokenResponse() {
            when(REFRESH_TOKEN_RESPONSE.isValid()).thenReturn(false);
            return withRefreshTokenMatters();
        }

        Member build() {
            member.setSalt(SALT);
            return member;
        }
    }

}