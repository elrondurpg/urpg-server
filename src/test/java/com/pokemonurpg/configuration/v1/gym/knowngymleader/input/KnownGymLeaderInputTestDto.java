package com.pokemonurpg.configuration.v1.gym.knowngymleader.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class KnownGymLeaderInputTestDto extends KnownGymLeaderInputDto {
    public KnownGymLeaderInputTestDto() {
        this.setName(RandomStringGenerator.generate());
    }
}
