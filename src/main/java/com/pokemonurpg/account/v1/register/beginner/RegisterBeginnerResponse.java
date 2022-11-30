package com.pokemonurpg.account.v1.register.beginner;

import com.pokemonurpg.account.v1.register.common.RegisterPlayerResponse;
import com.pokemonurpg.configuration.v1.create.pokemon.starter.CreateStarterPokemonResponse;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class RegisterBeginnerResponse extends RegisterPlayerResponse {
    private CreateStarterPokemonResponse starter;
}
