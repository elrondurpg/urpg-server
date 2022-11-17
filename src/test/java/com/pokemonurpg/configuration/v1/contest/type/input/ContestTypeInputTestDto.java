package com.pokemonurpg.configuration.v1.contest.type.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class ContestTypeInputTestDto extends ContestTypeInputDto {
    public ContestTypeInputTestDto() {
        this.setName(RandomStringGenerator.generate());
    }
}
