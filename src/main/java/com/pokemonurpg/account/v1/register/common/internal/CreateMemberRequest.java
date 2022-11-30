package com.pokemonurpg.account.v1.register.common.internal;

import com.pokemonurpg.account.v1.register.common.internal.CreateMemberRequest;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CreateMemberRequest {
    private String code;
    private String name;
    private String discordId;
    private OAuthAccessTokenResponse accessToken;
}
