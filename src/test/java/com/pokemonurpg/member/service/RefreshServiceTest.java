package com.pokemonurpg.member.service;

import com.pokemonurpg.security.dto.SessionDto;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.security.service.AesEncryptionService;
import com.pokemonurpg.security.service.OAuthService;
import com.pokemonurpg.security.service.RefreshService;
import com.pokemonurpg.security.service.SessionService;
import com.pokemonurpg.member.models.Member;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.inject.Provider;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RefreshServiceTest {
    private final static byte[] IV = { 4, 20, 69 };
    private final static String ENCRYPTED_REFRESH_TOKEN = "ENCRYPTED_REFRESH_TOKEN";
    private final static IvParameterSpec IV_PARAMETER_SPEC = mock(IvParameterSpec.class);
    private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final static int SALT = 3274382;
    private final static SecretKey SECRET_KEY = mock(SecretKey.class);
    private final static String DECRYPTED_REFRESH_TOKEN = "DECRYPTED_REFRESH_TOKEN";
    private final static OAuthAccessTokenResponse REFRESH_TOKEN_RESPONSE = mock(OAuthAccessTokenResponse.class);
    private final static String USERNAME = "USERNAME";
    private final static String DISCORD_ID = "DISCORD_ID";

    @InjectMocks
    private RefreshService refreshService;

    @Mock
    private MemberService memberService;

    @Mock
    private OAuthService oAuthService;

    @Mock
    private AesEncryptionService aesEncryptionService;

    @Mock
    private Provider<SessionService> sessionServiceProvider;

    private SessionService sessionService = mock(SessionService.class);
    private Member member;
    private SessionDto expectedResponse = new SessionDto();

    @BeforeEach
    public void init() {
        when(sessionServiceProvider.get()).thenReturn(sessionService);

        member = new Member();
        member.setRefreshTokenIv(IV);
        member.setRefreshToken(ENCRYPTED_REFRESH_TOKEN);
        member.setSalt(SALT);
        member.setName(USERNAME);
        member.setDiscordId(DISCORD_ID);

        expectedResponse.setUsername(USERNAME);
        expectedResponse.setId(DISCORD_ID);
        expectedResponse.setAccessToken(ACCESS_TOKEN);
    }

    @Test
    public void refresh() {
        when(sessionService.getAuthenticatedMember()).thenReturn(member);
        when(aesEncryptionService.createIvParameterSpec(IV)).thenReturn(IV_PARAMETER_SPEC);
        when(sessionService.getAccessToken()).thenReturn(ACCESS_TOKEN);
        when(aesEncryptionService.getKeyFromAccessToken(ACCESS_TOKEN, SALT)).thenReturn(SECRET_KEY);
        when(aesEncryptionService.decrypt(ENCRYPTED_REFRESH_TOKEN, SECRET_KEY, IV_PARAMETER_SPEC)).thenReturn(DECRYPTED_REFRESH_TOKEN);
        when(oAuthService.refreshAccessToken(DECRYPTED_REFRESH_TOKEN)).thenReturn(REFRESH_TOKEN_RESPONSE);
        when(REFRESH_TOKEN_RESPONSE.isValid()).thenReturn(true);
        when(REFRESH_TOKEN_RESPONSE.getAccessToken()).thenReturn(ACCESS_TOKEN);

        SessionDto response = refreshService.refresh();
        verify(memberService, times(1)).update(member, REFRESH_TOKEN_RESPONSE);
        assertTrue(EqualsBuilder.reflectionEquals(expectedResponse, response));
    }

    @Test
    public void refreshFailsWhenNoAuthenticatedMemberIsPresent() {
        when(sessionService.getAuthenticatedMember()).thenReturn(null);
        assertThrows(ResponseStatusException.class, () -> refreshService.refresh());
    }

    @Test
    public void refreshFailsWhenIvIsNull() {
        when(sessionService.getAuthenticatedMember()).thenReturn(member);
        when(aesEncryptionService.createIvParameterSpec(IV)).thenReturn(null);
        assertThrows(ResponseStatusException.class, () -> refreshService.refresh());
    }

    @Test
    public void refreshFailsWhenRefreshTokenIsNull() {
        when(sessionService.getAuthenticatedMember()).thenReturn(member);
        member.setRefreshToken(null);
        assertThrows(ResponseStatusException.class, () -> refreshService.refresh());
    }

    @Test
    public void refreshFailsWhenRefreshTokenResponseIsNull() {
        when(sessionService.getAuthenticatedMember()).thenReturn(member);
        when(aesEncryptionService.createIvParameterSpec(IV)).thenReturn(IV_PARAMETER_SPEC);
        when(sessionService.getAccessToken()).thenReturn(ACCESS_TOKEN);
        when(aesEncryptionService.getKeyFromAccessToken(ACCESS_TOKEN, SALT)).thenReturn(SECRET_KEY);
        when(aesEncryptionService.decrypt(ENCRYPTED_REFRESH_TOKEN, SECRET_KEY, IV_PARAMETER_SPEC)).thenReturn(DECRYPTED_REFRESH_TOKEN);
        when(oAuthService.refreshAccessToken(DECRYPTED_REFRESH_TOKEN)).thenReturn(null);
        assertThrows(ResponseStatusException.class, () -> refreshService.refresh());
    }

    @Test
    public void refreshFailsWhenRefreshTokenResponseIsInvalid() {
        when(sessionService.getAuthenticatedMember()).thenReturn(member);
        when(aesEncryptionService.createIvParameterSpec(IV)).thenReturn(IV_PARAMETER_SPEC);
        when(sessionService.getAccessToken()).thenReturn(ACCESS_TOKEN);
        when(aesEncryptionService.getKeyFromAccessToken(ACCESS_TOKEN, SALT)).thenReturn(SECRET_KEY);
        when(aesEncryptionService.decrypt(ENCRYPTED_REFRESH_TOKEN, SECRET_KEY, IV_PARAMETER_SPEC)).thenReturn(DECRYPTED_REFRESH_TOKEN);
        when(oAuthService.refreshAccessToken(DECRYPTED_REFRESH_TOKEN)).thenReturn(REFRESH_TOKEN_RESPONSE);
        when(REFRESH_TOKEN_RESPONSE.isValid()).thenReturn(false);
        assertThrows(ResponseStatusException.class, () -> refreshService.refresh());
    }
}