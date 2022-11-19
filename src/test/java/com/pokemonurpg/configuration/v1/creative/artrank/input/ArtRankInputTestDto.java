package com.pokemonurpg.configuration.v1.creative.artrank.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class ArtRankInputTestDto extends ArtRankInputDto {
    public ArtRankInputTestDto() {
        this.setName(RandomStringGenerator.generate());
        this.setRequirement(RandomStringGenerator.generate());
    }
}
