package com.pokemonurpg.species.input;

import com.pokemonurpg.configuration.v1.types.TypeInputDto;
import org.junit.Test;

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