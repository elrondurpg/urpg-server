package com.pokemonurpg.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemonurpg.security.models.DiscordUserResponse;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class OAuthService {

    @Resource
    private OAuthRequestBuilder oAuthRequestBuilder;

    @Resource
    private OkHttpClientService okHttpClientService;

    private Logger logger = LogManager.getLogger(OAuthService.class);

    private ObjectMapper mapper = new ObjectMapper();

    public OAuthAccessTokenResponse exchangeCodeForAccessToken(String code) {
        Request request = oAuthRequestBuilder.buildAccessTokenRequest(code);
        try {
            String response = okHttpClientService.sendRequest(request);
            return mapper.readValue(response, OAuthAccessTokenResponse.class);
        } catch (IOException e) {
            return null;
        }
    }

    public DiscordUserResponse getDiscordId(String accessToken) {
        Request request = oAuthRequestBuilder.buildDiscordIdRequest(accessToken);
        try {
            String response = okHttpClientService.sendRequest(request);
            return mapper.readValue(response, DiscordUserResponse.class);
        } catch (IOException ignored) {}
        return null;
    }

    public OAuthAccessTokenResponse refreshAccessToken(String refreshToken) {
        Request request = oAuthRequestBuilder.buildRefreshTokenRequest(refreshToken);
        try {
            String response = okHttpClientService.sendRequest(request);
            return mapper.readValue(response, OAuthAccessTokenResponse.class);
        } catch (IOException e) {
            return null;
        }
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }
}
