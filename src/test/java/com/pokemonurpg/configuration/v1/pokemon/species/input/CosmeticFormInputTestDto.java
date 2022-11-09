package com.pokemonurpg.configuration.v1.pokemon.species.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class CosmeticFormInputTestDto extends CosmeticFormInputDto {
    public CosmeticFormInputTestDto() {
        this.setName(RandomStringGenerator.generate());
        this.setFormName(RandomStringGenerator.generate());
    }
}
