package com.pokemonurpg.dto.security;

public class InviteUserDto {
    private String username;
    private String id;

    public InviteUserDto() {
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
}
