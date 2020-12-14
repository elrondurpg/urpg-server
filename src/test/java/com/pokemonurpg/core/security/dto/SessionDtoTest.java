package com.pokemonurpg.core.security.dto;

import org.junit.Test;

import static org.junit.Assert.*;

public class SessionDtoTest {
    private final static String USERNAME = "USERNAME";
    private final static String ACCESS_TOKEN = "ACCESS TOKEN";
    private final static String REFRESH_TOKEN = "REFRESH TOKEN";
    private final static String ID = "ID";

    @Test
    public void testConstructor() {
        SessionDto dto = new SessionDto(USERNAME, ID, ACCESS_TOKEN, REFRESH_TOKEN);
        assertEquals(USERNAME, dto.getUsername());
        assertEquals(ID, dto.getId());
        assertEquals(ACCESS_TOKEN, dto.getAccessToken());
        assertEquals(REFRESH_TOKEN, dto.getRefreshToken());
    }
}