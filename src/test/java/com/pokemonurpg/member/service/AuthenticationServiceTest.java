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
import org.junit.Before;
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
    private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final static String DISCORD_ID = "DISCORD_ID";

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private MemberService memberService;

    @Mock
    private BotAuthenticationService botAuthenticationService;

    @Mock
    private HumanAuthenticationService humanAuthenticationService;

    private Member member = new Member();

    @Test
    public void authenticateBot() {
        member.setBot(true);
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(member);
        when(botAuthenticationService.authenticate(member, ACCESS_TOKEN)).thenReturn(member);
        assertEquals(member, authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN));
    }

    @Test
    public void authenticateHuman() {
        member.setBot(false);
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(member);
        when(humanAuthenticationService.authenticate(member, ACCESS_TOKEN)).thenReturn(member);
        assertEquals(member, authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN));
    }

    @Test
    public void authenticationFailsWhenMemberNotFound() {
        when(memberService.findByDiscordId(DISCORD_ID)).thenReturn(null);
        assertNull(authenticationService.authenticate(DISCORD_ID, ACCESS_TOKEN));
    }

}