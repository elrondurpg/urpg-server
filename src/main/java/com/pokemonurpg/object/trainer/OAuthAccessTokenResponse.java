package com.pokemonurpg.object.trainer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthAccessTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private String expiresIn;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("error")
    private String error;
    @JsonProperty("error_description")
    private String errorDescription;

    public OAuthAccessTokenResponse() {}

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
