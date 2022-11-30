package com.pokemonurpg.member.service;

import com.pokemonurpg.security.service.AccessTokenVerificationService;
import com.pokemonurpg.security.service.HashService;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccessTokenVerificationServiceTest {
    private final static String PROVIDED_ACCESS_TOKEN = "PROVIDED_ACCESS_TOKEN";
    private final static String HASHED_ACCESS_TOKEN = "at83yu2oh2ljk";
    private final static String DIFFERENT_HASHED_ACCESS_TOKEN = "dfsafdsa";
    private final static Integer SALT = 5324;

    @InjectMocks
    private AccessTokenVerificationService accessTokenVerificationService;

    @Mock
    private HashService hashService;

    private Member member;

    @BeforeEach
    public void init() {
        member = new Member();
        member.setAccessToken(HASHED_ACCESS_TOKEN);
        member.setSalt(SALT);
    }

    @Test
    public void verify() {
        when(hashService.hash(PROVIDED_ACCESS_TOKEN + SALT)).thenReturn(HASHED_ACCESS_TOKEN);
        assertTrue(accessTokenVerificationService.verify(member, PROVIDED_ACCESS_TOKEN));
    }

    @Test
    public void verifyFailsWhenAccessTokenIsNull() {
        member.setAccessToken(null);
        assertFalse(accessTokenVerificationService.verify(member, PROVIDED_ACCESS_TOKEN));
    }

    @Test
    public void verifyFailsWhenAccessTokenIsEmpty() {
        member.setAccessToken("");
        assertFalse(accessTokenVerificationService.verify(member, PROVIDED_ACCESS_TOKEN));
    }

    @Test
    public void verifyFailsWhenHashServiceThrowsError() {
        when(hashService.hash(PROVIDED_ACCESS_TOKEN + SALT)).thenThrow(new IllegalStateException());
        assertFalse(accessTokenVerificationService.verify(member, PROVIDED_ACCESS_TOKEN));
    }

    @Test
    public void verifyFailsWhenAccessTokenIsWrong() {
        member.setAccessToken(DIFFERENT_HASHED_ACCESS_TOKEN);
        when(hashService.hash(PROVIDED_ACCESS_TOKEN + SALT)).thenReturn(HASHED_ACCESS_TOKEN);
        assertFalse(accessTokenVerificationService.verify(member, PROVIDED_ACCESS_TOKEN));
    }

}