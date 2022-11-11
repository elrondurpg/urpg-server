package com.pokemonurpg.configuration.v1.pokemon.type.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class TypeInputTestDto extends TypeInputDto {
    public TypeInputTestDto() {
        this.setName(RandomStringGenerator.generate());
    }
}
