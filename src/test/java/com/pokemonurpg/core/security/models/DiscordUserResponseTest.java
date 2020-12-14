package com.pokemonurpg.core.security.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiscordUserResponseTest {
    private final static String ID = "ID";
    private final static String USERNAME = "USERNAME";
    private final static String LOCALE = "LOCALE";
    private final static Boolean MFA_ENABLED = true;
    private final static String FLAGS = "FLAGS";
    private final static String PUBLIC_FLAGS = "PUBLIC_FLAGS";
    private final static String AVATAR = "AVATAR";
    private final static String DISCRIMINATOR = "DISCRIMINATOR";
    private final static String ERROR = "ERROR";
    private final static String ERROR_DESCRIPTION = "ERROR_DESCRIPTION";
    private final static String PREMIUM_TYPE = "PREMIUM_TYPE";
    private final static String TEST = "TEST";

    @Test
    public void testPojo() {
        DiscordUserResponse discordUserResponse = new DiscordUserResponse();
        discordUserResponse.setId(ID);
        discordUserResponse.setUsername(USERNAME);
        discordUserResponse.setLocale(LOCALE);
        discordUserResponse.setMfaEnabled(MFA_ENABLED);
        discordUserResponse.setFlags(FLAGS);
        discordUserResponse.setPublicFlags(PUBLIC_FLAGS);
        discordUserResponse.setAvatar(AVATAR);
        discordUserResponse.setDiscriminator(DISCRIMINATOR);
        discordUserResponse.setError(ERROR);
        discordUserResponse.setErrorDescription(ERROR_DESCRIPTION);
        discordUserResponse.setPremiumType(PREMIUM_TYPE);

        assertEquals(ID, discordUserResponse.getId());
        assertEquals(USERNAME, discordUserResponse.getUsername());
        assertEquals(LOCALE, discordUserResponse.getLocale());
        assertEquals(MFA_ENABLED, discordUserResponse.getMfaEnabled());
        assertEquals(FLAGS, discordUserResponse.getFlags());
        assertEquals(PUBLIC_FLAGS, discordUserResponse.getPublicFlags());
        assertEquals(AVATAR, discordUserResponse.getAvatar());
        assertEquals(DISCRIMINATOR, discordUserResponse.getDiscriminator());
        assertEquals(ERROR, discordUserResponse.getError());
        assertEquals(ERROR_DESCRIPTION, discordUserResponse.getErrorDescription());
        assertEquals(PREMIUM_TYPE, discordUserResponse.getPremiumType());
    }

    @Test
    public void invalidIfErrorIsNotNull() {
        DiscordUserResponse response = new DiscordUserResponse();
        response.setId(TEST);
        response.setError(TEST);
        assertFalse(response.isValid());
    }

    @Test
    public void invalidIfErrorDescriptionIsNotNull() {
        DiscordUserResponse response = new DiscordUserResponse();
        response.setId(TEST);
        response.setErrorDescription(TEST);
        assertFalse(response.isValid());
    }

    @Test
    public void invalidIfIdIsNull() {
        DiscordUserResponse response = new DiscordUserResponse();
        assertFalse(response.isValid());
    }

    @Test
    public void invalidIfIdIsEmpty() {
        DiscordUserResponse response = new DiscordUserResponse();
        response.setId("");
        assertFalse(response.isValid());
    }

    @Test
    public void validIfIdIsNotEmptyAndErrorsAre() {
        DiscordUserResponse response = new DiscordUserResponse();
        response.setId(TEST);
        assertTrue(response.isValid());
    }
}