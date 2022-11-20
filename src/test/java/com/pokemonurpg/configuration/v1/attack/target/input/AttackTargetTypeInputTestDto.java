package com.pokemonurpg.configuration.v1.attack.target.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class AttackTargetTypeInputTestDto extends AttackTargetTypeInputDto {

    public AttackTargetTypeInputTestDto() {
        setName(RandomStringGenerator.generate());
        setDescription(RandomStringGenerator.generate());
    }
}
