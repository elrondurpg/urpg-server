package com.pokemonurpg.configuration.v1.pokemon.type.model;

import org.junit.Test;

import com.pokemonurpg.configuration.v1.pokemon.type.input.TypeInputDto;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;

import static org.junit.Assert.*;

public class TypeTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2432;

    @Test
    public void testPojo() {
        Type type = new Type();
        type.setDbid(DBID);
        type.setName(NAME);

        assertEquals(DBID, type.getDbid());
        assertEquals(NAME, type.getName());
    }

    @Test
    public void testConstructor() {
        TypeInputDto input = new TypeInputDto();
        input.setName(NAME);

        Type type = new Type(input);

        assertEquals(NAME, type.getName());
    }
}