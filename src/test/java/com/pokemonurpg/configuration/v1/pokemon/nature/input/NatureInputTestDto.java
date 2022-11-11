package com.pokemonurpg.configuration.v1.pokemon.nature.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class NatureInputTestDto extends NatureInputDto {
    public NatureInputTestDto() {
        this.setName(RandomStringGenerator.generate());
    }
}
