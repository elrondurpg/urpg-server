package com.pokemonurpg.login.dto;

import javax.validation.constraints.NotEmpty;

public class SessionDto {
    @NotEmpty
    private String username;

    @NotEmpty
    private String id;

    @NotEmpty
    private String accessToken;

    public SessionDto() {
    }

    public SessionDto(String username, String id, String accessToken) {
        setUsername(username);
        setId(id);
        setAccessToken(accessToken);
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
}
