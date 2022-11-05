package com.pokemonurpg.configuration.v1.pokemon.type.input;

import org.junit.Test;

import com.pokemonurpg.configuration.v1.pokemon.type.input.TypeInputDto;

import static org.junit.Assert.*;

public class TypeInputDtoTest {
    private final static String NAME = "TEST";

    @Test
    public void testPojo() {
        TypeInputDto input = new TypeInputDto();
        input.setName(NAME);
        assertEquals(NAME, input.getName());
    }
}