package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.natures.NatureRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class NatureTest {
    private final static Integer DBID = 324;
    private final static String NAME = "NAME";

    @Test
    public void testPojo() {
        Nature nature = new Nature();
        nature.setDbid(DBID);
        nature.setName(NAME);

        assertEquals(DBID, nature.getDbid());
        assertEquals(NAME, nature.getName());
    }

    @Test
    public void testConstructor() {
        NatureRequest input = new NatureRequest();
        input.setName(NAME);

        Nature nature = new Nature(input);
        assertEquals(NAME, nature.getName());
    }

}