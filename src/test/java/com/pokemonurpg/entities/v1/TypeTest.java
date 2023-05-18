package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.types.TypeRequest;
import org.junit.Test;

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
        TypeRequest input = new TypeRequest();
        input.setName(NAME);

        Type type = new Type(input);

        assertEquals(NAME, type.getName());
    }
}