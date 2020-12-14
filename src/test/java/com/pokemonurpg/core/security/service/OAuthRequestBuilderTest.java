package com.pokemonurpg.core.security.service;

import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class OAuthRequestBuilderTest {
    private final static String CODE = "CODE";
    private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final static String REFRESH_TOKEN = "REFRESH_TOKEN";

    private OAuthRequestBuilder oAuthRequestBuilder = new OAuthRequestBuilder();

    @Before
    public void init() {
        OAuthRequestBuilder.setClientId("");
        OAuthRequestBuilder.setClientSecret("");
        OAuthRequestBuilder.setRedirectUri("");
    }

    @Test
    public void buildAccessTokenRequest() throws IOException {
        Buffer buffer = new Buffer();
        Request request = oAuthRequestBuilder.buildAccessTokenRequest(CODE);
        RequestBody body = request.body();
        body.writeTo(buffer);
        assertTrue(buffer.readUtf8().contains(CODE));
    }

    @Test
    public void buildDiscordIdRequest() {
        Request request = oAuthRequestBuilder.buildDiscordIdRequest(ACCESS_TOKEN);
        assertEquals("Bearer " + ACCESS_TOKEN, request.header("Authorization"));
    }

    @Test
    public void buildRefreshTokenRequest() throws IOException {
        Buffer buffer = new Buffer();
        Request request = oAuthRequestBuilder.buildRefreshTokenRequest(REFRESH_TOKEN);
        RequestBody body = request.body();
        body.writeTo(buffer);
        assertTrue(buffer.readUtf8().contains(REFRESH_TOKEN));
    }

}