package com.pokemonurpg.configuration.v1.contest.rse.input;

import com.pokemonurpg.test.RandomNumberGenerator;
import com.pokemonurpg.test.RandomStringGenerator;

public class RseContestMoveTypeInputTestDto extends RseContestMoveTypeInputDto {
    public RseContestMoveTypeInputTestDto() {
        setName(RandomStringGenerator.generate());
        setDescription(RandomStringGenerator.generate());
        setScore(RandomNumberGenerator.generate());
        setJam(RandomNumberGenerator.generate());
    }
}
