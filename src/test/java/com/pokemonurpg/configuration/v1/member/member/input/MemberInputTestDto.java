package com.pokemonurpg.configuration.v1.member.member.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class MemberInputTestDto extends MemberInputDto {
    public MemberInputTestDto() {
        this.setName(RandomStringGenerator.generate());
    }
}
