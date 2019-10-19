package com.pokemonurpg.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemonurpg.object.DiscordUser;
import com.pokemonurpg.object.OAuthAccessTokenResponse;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {
    private static final String CLIENT_ID = System.getenv("CLIENT_ID");
    private static final String CLIENT_SECRET = System.getenv("CLIENT_SECRET");
    public static final String REDIRECT_URI = System.getenv("REDIRECT_URI");

    private final OkHttpClient httpClient = new OkHttpClient();

    public OAuthAccessTokenResponse exchangeCodeForAccessToken(String code) {
        RequestBody formBody = new FormBody.Builder()
                .add("client_id", CLIENT_ID)
                .add("client_secret", CLIENT_SECRET)
                .add("grant_type", "authorization_code")
                .add("code", code)
                .add("redirect_uri", REDIRECT_URI)
                .add("scope", "identify")
                .build();

        Request request = new Request.Builder()
                .url("https://discordapp.com/api/oauth2/token")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(formBody)
                .build();

        OAuthAccessTokenResponse accessTokenResponse = null;
        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ObjectMapper objectMapper = new ObjectMapper();
                String responseBody = response.body().string();
                accessTokenResponse = objectMapper.readValue(responseBody, OAuthAccessTokenResponse.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return accessTokenResponse;
    }

    public String getDiscordId(String accessToken) {
        Request request = new Request.Builder()
                .url("https://discordapp.com/api/users/@me")
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        try (Response response = httpClient.newCall(request).execute()){
            if (response.isSuccessful()) {
                ObjectMapper objectMapper = new ObjectMapper();
                String responseBody = response.body().string();
                DiscordUser user = objectMapper.readValue(responseBody, DiscordUser.class);
                if (user.getError() != null || user.getErrorDescription() != null) {
                    throw new IllegalStateException(user.getErrorDescription());
                }
                if (user != null) {
                    return user.getId();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public OAuthAccessTokenResponse refreshAccessToken(String refreshToken) {
        RequestBody formBody = new FormBody.Builder()
                .add("client_id", CLIENT_ID)
                .add("client_secret", CLIENT_SECRET)
                .add("grant_type", "refresh_token")
                .add("refresh_token", refreshToken)
                .add("redirect_uri", REDIRECT_URI)
                .add("scope", "identify")
                .build();

        Request request = new Request.Builder()
                .url("https://discordapp.com/api/oauth2/token")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(formBody)
                .build();

        OAuthAccessTokenResponse accessTokenResponse = null;
        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ObjectMapper objectMapper = new ObjectMapper();
                String responseBody = response.body().string();
                accessTokenResponse = objectMapper.readValue(responseBody, OAuthAccessTokenResponse.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return accessTokenResponse;
    }
}
