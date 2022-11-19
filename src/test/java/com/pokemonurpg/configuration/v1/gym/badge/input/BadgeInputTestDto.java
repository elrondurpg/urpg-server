package com.pokemonurpg.configuration.v1.gym.badge.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class BadgeInputTestDto extends BadgeInputDto {
    public BadgeInputTestDto() {
        this.setName(RandomStringGenerator.generate());
    }
}
