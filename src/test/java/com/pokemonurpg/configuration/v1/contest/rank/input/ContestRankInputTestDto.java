package com.pokemonurpg.configuration.v1.contest.rank.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class ContestRankInputTestDto extends ContestRankInputDto {
    public ContestRankInputTestDto() {
        setName(RandomStringGenerator.generate());
    }
}
