package com.pokemonurpg.dto.security;

public class SessionDto {
    private String username;
    private String id;
    private String accessToken;
    private String refreshToken;

    public SessionDto() {
    }

    public SessionDto(String username, String id, String accessToken, String refreshToken) {
        this.username = username;
        this.id = id;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
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
