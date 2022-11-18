package com.pokemonurpg.configuration.v1.contest.oras.input;

import com.pokemonurpg.test.RandomNumberGenerator;
import com.pokemonurpg.test.RandomStringGenerator;

public class OrasContestMoveTypeInputTestDto extends OrasContestMoveTypeInputDto {
    public OrasContestMoveTypeInputTestDto() {
        setName(RandomStringGenerator.generate());
        setDescription(RandomStringGenerator.generate());
        setScore(RandomNumberGenerator.generate());
        setJam(RandomNumberGenerator.generate());
    }
}
