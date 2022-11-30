package com.pokemonurpg.account.v1.register.beginner;

import com.pokemonurpg.account.v1.register.beginner.internal.CreateStarterPokemonResponse;
import com.pokemonurpg.account.v1.register.common.RegisterMemberResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class RegisterBeginnerResponse extends RegisterMemberResponse {
    private CreateStarterPokemonResponse starter;
}
