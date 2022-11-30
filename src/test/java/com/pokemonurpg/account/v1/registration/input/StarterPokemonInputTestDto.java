package com.pokemonurpg.account.v1.registration.input;

import com.pokemonurpg.stats.input.StarterPokemonInputDto;
import com.pokemonurpg.test.RandomStringGenerator;

public class StarterPokemonInputTestDto extends StarterPokemonInputDto {
    public StarterPokemonInputTestDto() {
        setGender(RandomStringGenerator.generate());
        setSpecies(RandomStringGenerator.generate());
    }
}
