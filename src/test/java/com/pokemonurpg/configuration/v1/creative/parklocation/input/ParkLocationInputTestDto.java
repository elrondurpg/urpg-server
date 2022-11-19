package com.pokemonurpg.configuration.v1.creative.parklocation.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class ParkLocationInputTestDto extends ParkLocationInputDto {
    public ParkLocationInputTestDto() {
        this.setName(RandomStringGenerator.generate());
    }
}
