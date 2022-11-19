package com.pokemonurpg.configuration.v1.creative.storyrank.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class StoryRankInputTestDto extends StoryRankInputDto {
    public StoryRankInputTestDto() {
        this.setName(RandomStringGenerator.generate());
        this.setRequirement(RandomStringGenerator.generate());
    }
}
