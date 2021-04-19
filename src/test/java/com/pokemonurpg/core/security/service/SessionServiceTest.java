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

    private SessionService sessionService = new SessionService();

}