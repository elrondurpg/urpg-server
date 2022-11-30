package com.pokemonurpg.account.v1.register.common;

import java.util.List;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class RegisterPlayerResponse {
    private String name;
    private String discordId;
    private List<String> roles;
    private String accessToken;
}
