package com.pokemonurpg.configuration.v1.pokemon.species.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class SpeciesAbilityInputTestDto extends SpeciesAbilityInputDto {
    public SpeciesAbilityInputTestDto() {
        this.setName(RandomStringGenerator.generate());
        this.setHidden(true);
    }
    
}
