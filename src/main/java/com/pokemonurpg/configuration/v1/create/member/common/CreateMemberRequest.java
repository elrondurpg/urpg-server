package com.pokemonurpg.configuration.v1.create.member.common;

import com.pokemonurpg.configuration.v1.create.member.common.CreateMemberRequest;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CreateMemberRequest {
    private String code;
    private String name;
    private String discordId;
    private OAuthAccessTokenResponse accessToken;
}
