package com.pokemonurpg.member.service;

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
public class BotAuthenticationServiceTest {

    private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final static long SESSION_EXPIRE = 34234L;

    @InjectMocks
    private BotAuthenticationService botAuthenticationService;

    @Mock
    private AccessTokenVerificationService accessTokenVerificationService;

    @Mock
    private SessionExpirationService sessionExpirationService;

    private Member bot;

    @Before
    public void init() {
        bot = new Member();
        bot.setSessionExpire(SESSION_EXPIRE);
        bot.setBot(true);
    }

    @Test
    public void authenticate() {
        when(accessTokenVerificationService.verify(bot, ACCESS_TOKEN)).thenReturn(true);
        assertEquals(bot, botAuthenticationService.authenticate(bot, ACCESS_TOKEN));
    }

    @Test
    public void authenticationFailsMemberNull() {
        assertNull(botAuthenticationService.authenticate(null, ACCESS_TOKEN));
    }

    @Test
    public void authenticationFailsMemberIsNotBot() {
        bot.setBot(false);
        assertNull(botAuthenticationService.authenticate(bot, ACCESS_TOKEN));
    }

    @Test
    public void authenticationFailsAccessTokenWrong() {
        when(accessTokenVerificationService.verify(bot, ACCESS_TOKEN)).thenReturn(false);
        assertNull(botAuthenticationService.authenticate(bot, ACCESS_TOKEN));
    }
}