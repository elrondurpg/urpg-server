package com.pokemonurpg.account.v1.registration.input;

import com.pokemonurpg.configuration.v1.create.pokemon.starter.CreateStarterPokemonRequest;
import com.pokemonurpg.test.RandomStringGenerator;

public class StarterPokemonInputTestDto extends CreateStarterPokemonRequest {
    public StarterPokemonInputTestDto() {
        setGender(RandomStringGenerator.generate());
        setSpecies(RandomStringGenerator.generate());
    }
}
