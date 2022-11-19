package com.pokemonurpg.configuration.v1.creative.parkrank.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class ParkRankInputTestDto extends ParkRankInputDto {
    public ParkRankInputTestDto() {
        this.setName(RandomStringGenerator.generate());
        this.setRequirement(RandomStringGenerator.generate());
    }
}
