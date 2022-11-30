package com.pokemonurpg.configuration.v1.create.member.common;

import java.util.List;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CreateMemberResponse {
    private String accessToken;
    private String name;
    private String discordId;
    private List<String> roles;
}
