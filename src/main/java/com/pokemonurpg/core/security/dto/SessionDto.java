package com.pokemonurpg.core.security.dto;

import com.pokemonurpg.core.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.member.models.Member;

import javax.validation.constraints.NotEmpty;

public class SessionDto {
    @NotEmpty
    private String username;

    @NotEmpty
    private String id;

    @NotEmpty
    private String accessToken;

    @NotEmpty
    private String refreshToken;

    public SessionDto() {
    }

    public SessionDto(String username, String id, String accessToken, String refreshToken) {
        setUsername(username);
        setId(id);
        setAccessToken(accessToken);
        setRefreshToken(refreshToken);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
