package com.pokemonurpg.dto.security;

public class RegisterBetaDto {
    private String username;
    private String betaKey;
    private String password;

    public RegisterBetaDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBetaKey() {
        return betaKey;
    }

    public void setBetaKey(String betaKey) {
        this.betaKey = betaKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
