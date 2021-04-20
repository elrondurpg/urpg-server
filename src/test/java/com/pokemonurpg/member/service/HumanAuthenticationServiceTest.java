package com.pokemonurpg.member.service;

import com.pokemonurpg.security.service.AccessTokenVerificationService;
import com.pokemonurpg.security.service.HumanAuthenticationService;
import com.pokemonurpg.security.service.SessionExpirationService;
import com.pokemonurpg.member.models.Member;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HumanAuthenticationServiceTest {

    private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final static long SESSION_EXPIRE = 34234L;

    @InjectMocks
    private HumanAuthenticationService humanAuthenticationService;

    @Mock
    private AccessTokenVerificationService accessTokenVerificationService;

    @Mock
    private SessionExpirationService sessionExpirationService;

    private Member member;

    @Before
    public void init() {
        member = new Member();
        member.setSessionExpire(SESSION_EXPIRE);
        member.setBot(false);
    }

    @Test
    public void authenticate() {
        when(accessTokenVerificationService.verify(member, ACCESS_TOKEN)).thenReturn(true);
        when(sessionExpirationService.isExpired(SESSION_EXPIRE)).thenReturn(false);

        assertEquals(member, humanAuthenticationService.authenticate(member, ACCESS_TOKEN));
    }

    @Test
    public void authenticationFailsMemberNull() {
        assertNull(humanAuthenticationService.authenticate(null, ACCESS_TOKEN));
    }

    @Test
    public void authenticationFailsMemberIsBot() {
        member.setBot(true);
        assertNull(humanAuthenticationService.authenticate(member, ACCESS_TOKEN));
    }

    @Test
    public void authenticationFailsAccessTokenWrong() {
        when(accessTokenVerificationService.verify(member, ACCESS_TOKEN)).thenReturn(false);
        assertNull(humanAuthenticationService.authenticate(member, ACCESS_TOKEN));
    }

    @Test
    public void authenticationFailsSessionExpired() {
        when(accessTokenVerificationService.verify(member, ACCESS_TOKEN)).thenReturn(true);
        when(sessionExpirationService.isExpired(SESSION_EXPIRE)).thenReturn(true);

        assertNull(humanAuthenticationService.authenticate(member, ACCESS_TOKEN));
    }

}