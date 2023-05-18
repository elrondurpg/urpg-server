package com.pokemonurpg.login.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemonurpg.login.v1.*;
import okhttp3.Request;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OAuthServiceTest {
    private final static String CODE = "CODE";
    private final static Request REQUEST = new Request.Builder()
            .url("http://google.com")
            .build();
    private final static String RESPONSE = "RESPONSE";
    private final static OAuthAccessTokenResponse O_AUTH_ACCESS_TOKEN_RESPONSE = mock(OAuthAccessTokenResponse.class);
    private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final static DiscordUserResponse DISCORD_USER_RESPONSE = mock(DiscordUserResponse.class);
    private final static String REFRESH_TOKEN = "REFRESH_TOKEN";

    private final static String ID = "ID";
    private final static String SECRET = "SECRET";

    @InjectMocks
    private OAuthService oAuthService;

    @Mock
    private OAuthRequestBuilder oAuthRequestBuilder;

    @Mock
    private OkHttpClientService okHttpClientService;

    private ObjectMapper objectMapper = mock(ObjectMapper.class);

    @Before
    public void init() {
        oAuthService.setMapper(objectMapper);
    }

    @Test
    public void exchangeCodeForAccessToken() throws IOException {
        when(oAuthRequestBuilder.buildAccessTokenRequest(CODE)).thenReturn(REQUEST);
        when(okHttpClientService.sendRequest(REQUEST)).thenReturn(RESPONSE);
        when(objectMapper.readValue(RESPONSE, OAuthAccessTokenResponse.class)).thenReturn(O_AUTH_ACCESS_TOKEN_RESPONSE);
        assertEquals(O_AUTH_ACCESS_TOKEN_RESPONSE, oAuthService.exchangeCodeForAccessToken(CODE));
    }

    @Test
    public void failExchangeCodeForAccessToken() throws IOException {
        when(oAuthRequestBuilder.buildAccessTokenRequest(CODE)).thenReturn(REQUEST);
        when(okHttpClientService.sendRequest(REQUEST)).thenThrow(IOException.class);
        assertNull(oAuthService.exchangeCodeForAccessToken(CODE));
    }

    @Test
    public void getDiscordId() throws IOException {
        when(oAuthRequestBuilder.buildDiscordIdRequest(ACCESS_TOKEN)).thenReturn(REQUEST);
        when(okHttpClientService.sendRequest(REQUEST)).thenReturn(RESPONSE);
        when(objectMapper.readValue(RESPONSE, DiscordUserResponse.class)).thenReturn(DISCORD_USER_RESPONSE);
        assertEquals(DISCORD_USER_RESPONSE, oAuthService.getDiscordId(ACCESS_TOKEN));
    }

    @Test
    public void failGetDiscordId() throws IOException {
        when(oAuthRequestBuilder.buildDiscordIdRequest(ACCESS_TOKEN)).thenReturn(REQUEST);
        when(okHttpClientService.sendRequest(REQUEST)).thenThrow(IOException.class);
        assertNull(oAuthService.getDiscordId(ACCESS_TOKEN));
    }

    @Test
    public void refreshAccessToken() throws IOException {
        when(oAuthRequestBuilder.buildRefreshTokenRequest(REFRESH_TOKEN)).thenReturn(REQUEST);
        when(okHttpClientService.sendRequest(REQUEST)).thenReturn(RESPONSE);
        when(objectMapper.readValue(RESPONSE, OAuthAccessTokenResponse.class)).thenReturn(O_AUTH_ACCESS_TOKEN_RESPONSE);
        assertEquals(O_AUTH_ACCESS_TOKEN_RESPONSE, oAuthService.refreshAccessToken(REFRESH_TOKEN));
    }

    @Test
    public void failRefreshAccessToken() throws IOException {
        when(oAuthRequestBuilder.buildRefreshTokenRequest(REFRESH_TOKEN)).thenReturn(REQUEST);
        when(okHttpClientService.sendRequest(REQUEST)).thenThrow(IOException.class);
        assertNull(oAuthService.refreshAccessToken(REFRESH_TOKEN));
    }

    @Test
    public void getAccessTokenForClientCredentials() throws IOException {
        when(oAuthRequestBuilder.buildClientCredentialsRequest(ID, SECRET)).thenReturn(REQUEST);
        when(okHttpClientService.sendRequest(REQUEST)).thenReturn(RESPONSE);
        when(objectMapper.readValue(RESPONSE, OAuthAccessTokenResponse.class)).thenReturn(O_AUTH_ACCESS_TOKEN_RESPONSE);
        assertEquals(O_AUTH_ACCESS_TOKEN_RESPONSE, oAuthService.getAccessTokenForClientCredentials(ID, SECRET));
    }

    @Test
    public void failGetAccessTokenForClientCredentials() throws IOException {
        when(oAuthRequestBuilder.buildClientCredentialsRequest(ID, SECRET)).thenReturn(REQUEST);
        when(okHttpClientService.sendRequest(REQUEST)).thenThrow(IOException.class);
        assertNull(oAuthService.getAccessTokenForClientCredentials(ID, SECRET));
    }

}