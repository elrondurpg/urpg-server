package com.pokemonurpg.account.v1.register.beginner.internal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class CreateStarterPokemonResponse {
    private String species;
    private String gender;
}
