package com.pokemonurpg.security.models;

public class OAuthAccessTokenResponseFake extends OAuthAccessTokenResponse {
    public final static String ACCESS_TOKEN = "ACCESS_TOKEN";

    public OAuthAccessTokenResponseFake() {
        this.setAccessToken(ACCESS_TOKEN);
    }
    
}
