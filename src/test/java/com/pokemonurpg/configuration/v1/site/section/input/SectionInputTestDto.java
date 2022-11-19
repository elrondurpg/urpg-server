package com.pokemonurpg.configuration.v1.site.section.input;

import com.pokemonurpg.test.RandomNumberGenerator;
import com.pokemonurpg.test.RandomStringGenerator;

public class SectionInputTestDto extends SectionInputDto {
    public SectionInputTestDto() {
        this.setName(RandomStringGenerator.generate());
        this.setTier1LegendaryRequirement(RandomNumberGenerator.generate());
        this.setTier2LegendaryRequirement(RandomNumberGenerator.generate());
    }
}
