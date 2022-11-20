package com.pokemonurpg.configuration.v1.gym.league.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class LeagueInputTestDto extends LeagueInputDto {
    public LeagueInputTestDto() {
        this.setName(RandomStringGenerator.generate());
    }
}
