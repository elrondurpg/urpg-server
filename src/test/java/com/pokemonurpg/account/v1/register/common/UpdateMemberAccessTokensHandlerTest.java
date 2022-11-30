package com.pokemonurpg.account.v1.register.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.configuration.v1.update.member.common.UpdateMemberAccessTokensHandler;
import com.pokemonurpg.core.service.SystemService;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.security.service.AesEncryptionService;
import com.pokemonurpg.security.service.HashService;
import com.pokemonurpg.test.RandomNumberGenerator;
import com.pokemonurpg.test.RandomStringGenerator;

@ExtendWith(MockitoExtension.class)
public class UpdateMemberAccessTokensHandlerTest {
    private final static String HASHED_ACCESS_TOKEN = RandomStringGenerator.generate();
    private final static String ACCESS_TOKEN = RandomStringGenerator.generate();
    private final static Integer SALT = RandomNumberGenerator.generate();
    private final static Long CURRENT_TIME = RandomNumberGenerator.generateLong();
    private final static byte[] IV_BYTE = new byte[] {} ;
    private final static javax.crypto.spec.IvParameterSpec IV = new IvParameterSpec(IV_BYTE);
    private final static SecretKey KEY = new SecretKeySpec(IV_BYTE, "DES");
    private final static String EXPIRES_IN = "" + RandomNumberGenerator.generateLong();
    private final static String REFRESH_TOKEN = RandomStringGenerator.generate();
    private final static String ENCRYPTED_REFRESH_TOKEN = RandomStringGenerator.generate();

    @InjectMocks
    private UpdateMemberAccessTokensHandler service;

    @Mock
    private HashService hashService;

    @Mock
    private AesEncryptionService aesEncryptionService;

    @Mock
    private SystemService systemService;

    @Test
    public void test_updateLoginInformation() {
        OAuthAccessTokenResponse token = new OAuthAccessTokenResponse();
        token.setAccessToken(ACCESS_TOKEN);
        token.setExpiresIn(EXPIRES_IN);
        token.setRefreshToken(REFRESH_TOKEN);

        Member member = new Member();
        member.setSalt(SALT);

        when(hashService.hash(ACCESS_TOKEN + SALT)).thenReturn(HASHED_ACCESS_TOKEN);
        when(systemService.currentTimeMillis()).thenReturn(CURRENT_TIME);
        when(aesEncryptionService.createIvParameterSpec()).thenReturn(IV);
        when(aesEncryptionService.getKeyFromAccessToken(ACCESS_TOKEN, SALT)).thenReturn(KEY);
        when(aesEncryptionService.encrypt(REFRESH_TOKEN, KEY, IV)).thenReturn(ENCRYPTED_REFRESH_TOKEN);

        assertEquals(HASHED_ACCESS_TOKEN, member.getAccessToken());
        assertEquals(Long.parseLong(EXPIRES_IN) + (CURRENT_TIME / 1000), member.getSessionExpire());
        assertEquals(ENCRYPTED_REFRESH_TOKEN, member.getRefreshToken());
        assertEquals(IV_BYTE, member.getRefreshTokenIv());
    }

    @Test
    public void test_updateLoginInformation_whenRefreshTokenNull() {
        OAuthAccessTokenResponse token = new OAuthAccessTokenResponse();
        token.setAccessToken(ACCESS_TOKEN);
        token.setExpiresIn(EXPIRES_IN);
        token.setRefreshToken(null);

        Member member = new Member();
        member.setSalt(SALT);

        when(hashService.hash(ACCESS_TOKEN + SALT)).thenReturn(HASHED_ACCESS_TOKEN);
        when(systemService.currentTimeMillis()).thenReturn(CURRENT_TIME);
        
        assertEquals(HASHED_ACCESS_TOKEN, member.getAccessToken());
        assertEquals(Long.parseLong(EXPIRES_IN) + (CURRENT_TIME / 1000), member.getSessionExpire());
        assertEquals(null, member.getRefreshToken());
        assertEquals(null, member.getRefreshTokenIv());
    }
}
