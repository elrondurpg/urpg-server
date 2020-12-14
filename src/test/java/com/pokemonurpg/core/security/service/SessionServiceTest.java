package com.pokemonurpg.core.security.service;

import com.pokemonurpg.core.security.dto.SessionDto;
import com.pokemonurpg.core.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.member.models.Member;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SessionServiceTest {
    private final static Member MEMBER = mock(Member.class);
    private final static OAuthAccessTokenResponse ACCESS_TOKEN_RESPONSE = mock(OAuthAccessTokenResponse.class);
    private final static String USERNAME = "USERNAME";
    private final static String DISCORD_ID = "DISCORD_ID";
    private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final static String REFRESH_TOKEN = "REFRESH_TOKEN";

    private SessionService sessionService = new SessionService();

    @Test
    public void create() {
        when(MEMBER.getUsername()).thenReturn(USERNAME);
        when(MEMBER.getDiscordId()).thenReturn(DISCORD_ID);
        when(ACCESS_TOKEN_RESPONSE.getAccessToken()).thenReturn(ACCESS_TOKEN);
        when(ACCESS_TOKEN_RESPONSE.getRefreshToken()).thenReturn(REFRESH_TOKEN);

        SessionDto session = sessionService.create(MEMBER, ACCESS_TOKEN_RESPONSE);
        assertEquals(USERNAME, session.getUsername());
        assertEquals(DISCORD_ID, session.getId());
        assertEquals(ACCESS_TOKEN, session.getAccessToken());
        assertEquals(REFRESH_TOKEN, session.getRefreshToken());

    }

}