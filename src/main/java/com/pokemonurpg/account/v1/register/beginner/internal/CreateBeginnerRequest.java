package com.pokemonurpg.account.v1.register.beginner.internal;

import com.pokemonurpg.account.v1.register.common.internal.CreateMemberRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CreateBeginnerRequest extends CreateMemberRequest {
    private CreateStarterPokemonRequest starter;
}
