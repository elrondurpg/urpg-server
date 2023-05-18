package com.pokemonurpg.login.v1;

import org.springframework.stereotype.Service;
import okhttp3.*;
@Service
public class OAuthRequestBuilder {
    private static String CLIENT_ID = System.getenv("CLIENT_ID");
    private static String CLIENT_SECRET = System.getenv("CLIENT_SECRET");
    private static String REDIRECT_URI = System.getenv("REDIRECT_URI");

    public Request buildAccessTokenRequest(String code) {
        RequestBody formBody = new FormBody.Builder()
                .add("client_id", CLIENT_ID)
                .add("client_secret", CLIENT_SECRET)
                .add("grant_type", "authorization_code")
                .add("code", code)
                .add("redirect_uri", REDIRECT_URI)
                .add("scope", "identify")
                .build();

        return new Request.Builder()
                .url("https://discordapp.com/api/oauth2/token")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(formBody)
                .build();
    }

    public Request buildDiscordIdRequest(String accessToken) {
        return new Request.Builder()
                .url("https://discordapp.com/api/users/@me")
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();
    }

    public Request buildRefreshTokenRequest(String refreshToken) {
        RequestBody formBody = new FormBody.Builder()
                .add("client_id", CLIENT_ID)
                .add("client_secret", CLIENT_SECRET)
                .add("grant_type", "refresh_token")
                .add("refresh_token", refreshToken)
                .add("redirect_uri", REDIRECT_URI)
                .add("scope", "identify")
                .build();

        return new Request.Builder()
                .url("https://discordapp.com/api/oauth2/token")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(formBody)
                .build();
    }

    public Request buildClientCredentialsRequest(String id, String secret) {
        RequestBody formBody = new FormBody.Builder()
            .add("grant_type", "client_credentials")
            .add("scope", "bot identify")
            .build();

        return new Request.Builder()
            .url("https://discordapp.com/api/oauth2/token")
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .header("Authorization", Credentials.basic(id, secret))
            .post(formBody)
            .build();
    }

    public static void setClientId(String clientId) {
        CLIENT_ID = clientId;
    }

    public static void setClientSecret(String clientSecret) {
        CLIENT_SECRET = clientSecret;
    }

    public static void setRedirectUri(String redirectUri) {
        REDIRECT_URI = redirectUri;
    }
}
