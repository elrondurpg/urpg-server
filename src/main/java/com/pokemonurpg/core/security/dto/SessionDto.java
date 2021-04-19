package com.pokemonurpg.core.security.dto;

import com.pokemonurpg.core.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.member.models.Member;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionDto that = (SessionDto) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(id, that.id) &&
                Objects.equals(accessToken, that.accessToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, id, accessToken);
    }
}
