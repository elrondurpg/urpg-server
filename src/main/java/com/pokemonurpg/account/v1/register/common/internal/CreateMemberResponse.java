package com.pokemonurpg.account.v1.register.common.internal;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CreateMemberResponse {
    private String accessToken;
    private String name;
    private String discordId;
    private List<String> roles;
}
