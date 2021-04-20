package com.pokemonurpg.security.dto;

import org.junit.Test;

import static org.junit.Assert.*;

public class SessionDtoTest {
    private final static String USERNAME = "USERNAME";
    private final static String ACCESS_TOKEN = "ACCESS TOKEN";
    private final static String ID = "ID";

    @Test
    public void testConstructor() {
        SessionDto dto = new SessionDto(USERNAME, ID, ACCESS_TOKEN);
        assertEquals(USERNAME, dto.getUsername());
        assertEquals(ID, dto.getId());
        assertEquals(ACCESS_TOKEN, dto.getAccessToken());
    }
}