package com.pokemonurpg.configuration.v1.pokemon.species.input;

import com.pokemonurpg.test.RandomNumberGenerator;
import com.pokemonurpg.test.RandomStringGenerator;

public class SpeciesAttackInputTestDto extends SpeciesAttackInputDto {
    public SpeciesAttackInputTestDto() {
        this.setName(RandomStringGenerator.generate());
        this.setMethod(RandomStringGenerator.generate());
        this.setGeneration(RandomNumberGenerator.generate());
    }
}
