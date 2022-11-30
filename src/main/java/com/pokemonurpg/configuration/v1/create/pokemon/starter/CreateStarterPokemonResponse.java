package com.pokemonurpg.configuration.v1.create.pokemon.starter;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CreateStarterPokemonResponse {
    private String species;
    private String gender;
}
