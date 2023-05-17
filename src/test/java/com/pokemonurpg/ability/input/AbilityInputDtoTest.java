package com.pokemonurpg.ability.input;

import com.pokemonurpg.configuration.v1.abilities.AbilityInputDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbilityInputDtoTest {
    private final static String NAME = "ABILITY";
    private final static String DESCRIPTION = "THIS IS A TEST";

    private AbilityInputDto abilityInputDto = new AbilityInputDto();

    @Test
    public void testPojo() {
        abilityInputDto.setName(NAME);
        abilityInputDto.setDescription(DESCRIPTION);

        assertEquals(NAME, abilityInputDto.getName());
        assertEquals(DESCRIPTION, abilityInputDto.getDescription());
    }

}