package com.pokemonurpg.account.v1.register;

import com.pokemonurpg.account.v1.register.CreatePlayerDto;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class CreatePlayerDto<Input extends RegisterPlayerDto> {
    private Input input;
    private String discordId;
    private OAuthAccessTokenResponse accessToken;
}
