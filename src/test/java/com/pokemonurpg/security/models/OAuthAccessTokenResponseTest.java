package com.pokemonurpg.security.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class OAuthAccessTokenResponseTest {
    private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final static String SCOPE = "SCOPE";
    private final static String TOKEN_TYPE = "TOKEN_TYPE";
    private final static String EXPIRES_IN = "EXPIRES_IN";
    private final static String REFRESH_TOKEN = "REFRESH_TOKEN";
    private final static String ERROR = "ERROR";
    private final static String ERROR_DESCRIPTION = "ERROR_DESCRIPTION";

    @Test
    public void testPojo() {
        OAuthAccessTokenResponse oAuthAccessTokenResponse = new OAuthAccessTokenResponse();
        oAuthAccessTokenResponse.setAccessToken(ACCESS_TOKEN);
        oAuthAccessTokenResponse.setScope(SCOPE);
        oAuthAccessTokenResponse.setTokenType(TOKEN_TYPE);
        oAuthAccessTokenResponse.setExpiresIn(EXPIRES_IN);
        oAuthAccessTokenResponse.setRefreshToken(REFRESH_TOKEN);
        oAuthAccessTokenResponse.setError(ERROR);
        oAuthAccessTokenResponse.setErrorDescription(ERROR_DESCRIPTION);

        assertEquals(ACCESS_TOKEN, oAuthAccessTokenResponse.getAccessToken());
        assertEquals(SCOPE, oAuthAccessTokenResponse.getScope());
        assertEquals(TOKEN_TYPE, oAuthAccessTokenResponse.getTokenType());
        assertEquals(EXPIRES_IN, oAuthAccessTokenResponse.getExpiresIn());
        assertEquals(REFRESH_TOKEN, oAuthAccessTokenResponse.getRefreshToken());
        assertEquals(ERROR, oAuthAccessTokenResponse.getError());
        assertEquals(ERROR_DESCRIPTION, oAuthAccessTokenResponse.getErrorDescription());
    }

    @Test
    public void invalidIfAccessTokenIsNull() {
        OAuthAccessTokenResponse accessTokenResponse = new OAuthAccessTokenResponse();
        assertFalse(accessTokenResponse.isValid());
    }

    @Test
    public void invalidIfAccessTokenIsEmpty() {
        OAuthAccessTokenResponse accessTokenResponse = new OAuthAccessTokenResponse();
        accessTokenResponse.setAccessToken("");
        assertFalse(accessTokenResponse.isValid());
    }

    @Test
    public void invalidIfRefreshTokenIsNull() {
        OAuthAccessTokenResponse accessTokenResponse = new OAuthAccessTokenResponse();
        accessTokenResponse.setAccessToken(ACCESS_TOKEN);
        assertFalse(accessTokenResponse.isValid());
    }

    @Test
    public void invalidIfRefreshTokenIsEmpty() {
        OAuthAccessTokenResponse accessTokenResponse = new OAuthAccessTokenResponse();
        accessTokenResponse.setAccessToken(ACCESS_TOKEN);
        accessTokenResponse.setRefreshToken("");
        assertFalse(accessTokenResponse.isValid());
    }

    @Test
    public void invalidIfExpiresInIsNull() {
        OAuthAccessTokenResponse accessTokenResponse = new OAuthAccessTokenResponse();
        accessTokenResponse.setAccessToken(ACCESS_TOKEN);
        accessTokenResponse.setRefreshToken(REFRESH_TOKEN);
        assertFalse(accessTokenResponse.isValid());
    }

    @Test
    public void invalidIfExpiresInIsEmpty() {
        OAuthAccessTokenResponse accessTokenResponse = new OAuthAccessTokenResponse();
        accessTokenResponse.setAccessToken(ACCESS_TOKEN);
        accessTokenResponse.setRefreshToken(REFRESH_TOKEN);
        accessTokenResponse.setExpiresIn("");
        assertFalse(accessTokenResponse.isValid());
    }

    @Test
    public void invalidIfErrorIsNotNull() {
        OAuthAccessTokenResponse accessTokenResponse = new OAuthAccessTokenResponse();
        accessTokenResponse.setAccessToken(ACCESS_TOKEN);
        accessTokenResponse.setRefreshToken(REFRESH_TOKEN);
        accessTokenResponse.setExpiresIn(EXPIRES_IN);
        accessTokenResponse.setError(ERROR);
        assertFalse(accessTokenResponse.isValid());
    }

    @Test
    public void invalidIfErrorDescriptionIsNotNull() {
        OAuthAccessTokenResponse accessTokenResponse = new OAuthAccessTokenResponse();
        accessTokenResponse.setAccessToken(ACCESS_TOKEN);
        accessTokenResponse.setRefreshToken(REFRESH_TOKEN);
        accessTokenResponse.setExpiresIn(EXPIRES_IN);
        accessTokenResponse.setErrorDescription(ERROR_DESCRIPTION);
        assertFalse(accessTokenResponse.isValid());
    }

    @Test
    public void validIfRequiredFieldsAreNotNullAndErrorsAre() {
        OAuthAccessTokenResponse accessTokenResponse = new OAuthAccessTokenResponse();
        accessTokenResponse.setAccessToken(ACCESS_TOKEN);
        accessTokenResponse.setRefreshToken(REFRESH_TOKEN);
        accessTokenResponse.setExpiresIn(EXPIRES_IN);
        assertTrue(accessTokenResponse.isValid());
    }
}