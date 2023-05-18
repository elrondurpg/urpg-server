package com.pokemonurpg.general.models;

import com.pokemonurpg.configuration.v1.capturemethods.ObtainedInputDto;
import com.pokemonurpg.entities.v1.Obtained;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObtainedTest {
    private final static Integer DBID = 324;
    private final static String NAME = "NAME";

    @Test
    public void testPojo() {
        Obtained obtained = new Obtained();
        obtained.setDbid(DBID);
        obtained.setName(NAME);

        assertEquals(DBID, obtained.getDbid());
        assertEquals(NAME, obtained.getName());
    }

    @Test
    public void testConstructor() {
        ObtainedInputDto input = new ObtainedInputDto();
        input.setName(NAME);

        Obtained obtained = new Obtained(input);
        assertEquals(NAME, obtained.getName());
    }

}