package com.pokemonurpg.configuration.v1.site.flag.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class FlagInputTestDto extends FlagInputDto {
    public FlagInputTestDto() {
        this.setName(RandomStringGenerator.generate());
        this.setDescription(RandomStringGenerator.generate());
        this.setType(RandomStringGenerator.generate());
        this.setValue(RandomStringGenerator.generate());
    }
}
