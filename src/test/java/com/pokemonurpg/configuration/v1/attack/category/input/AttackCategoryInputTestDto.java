package com.pokemonurpg.configuration.v1.attack.category.input;

import com.pokemonurpg.test.RandomStringGenerator;

public class AttackCategoryInputTestDto extends AttackCategoryInputDto {

    public AttackCategoryInputTestDto() {
        setName(RandomStringGenerator.generate());
    }
}
