package com.pokemonurpg.configuration.v1.gym.knownchampion.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class KnownChampionInputTestDto extends KnownChampionInputDto {
    public KnownChampionInputTestDto() {
        this.setName(RandomStringGenerator.generate());
    }
}
