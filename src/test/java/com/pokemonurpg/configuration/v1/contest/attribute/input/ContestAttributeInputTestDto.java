package com.pokemonurpg.configuration.v1.contest.attribute.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class ContestAttributeInputTestDto extends ContestAttributeInputDto {
    public ContestAttributeInputTestDto() {
        setName(RandomStringGenerator.generate());
    }
}
