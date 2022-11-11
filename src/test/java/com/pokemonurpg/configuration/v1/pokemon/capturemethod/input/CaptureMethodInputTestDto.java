package com.pokemonurpg.configuration.v1.pokemon.capturemethod.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class CaptureMethodInputTestDto extends CaptureMethodInputDto {
    public CaptureMethodInputTestDto() {
        this.setName(RandomStringGenerator.generate());
    }
}
