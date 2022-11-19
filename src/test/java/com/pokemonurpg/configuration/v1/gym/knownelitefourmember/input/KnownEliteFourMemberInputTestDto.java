package com.pokemonurpg.configuration.v1.gym.knownelitefourmember.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class KnownEliteFourMemberInputTestDto extends KnownEliteFourMemberInputDto {
    public KnownEliteFourMemberInputTestDto() {
        this.setName(RandomStringGenerator.generate());
    }
}
