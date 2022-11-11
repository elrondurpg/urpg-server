package com.pokemonurpg.configuration.v1.pokemon.ability.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class AbilityInputTestDto extends AbilityInputDto {
    public AbilityInputTestDto() {
        this.setName(RandomStringGenerator.generate());
        this.setDescription(RandomStringGenerator.generate());
    }
}
