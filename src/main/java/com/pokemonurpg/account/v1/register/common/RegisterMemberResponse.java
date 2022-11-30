package com.pokemonurpg.account.v1.register.common;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class RegisterMemberResponse {
    private String name;
    private String discordId;
    private List<String> roles;
    private String accessToken;
}
