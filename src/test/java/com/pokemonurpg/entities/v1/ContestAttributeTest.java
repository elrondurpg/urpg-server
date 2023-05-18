package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.contestattributes.ContestAttributeInputDto;
import com.pokemonurpg.entities.v1.ContestAttribute;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContestAttributeTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2432;

    @Test
    public void testPojo() {
        ContestAttribute contestAttribute = new ContestAttribute();
        contestAttribute.setDbid(DBID);
        contestAttribute.setName(NAME);

        assertEquals(DBID, contestAttribute.getDbid());
        assertEquals(NAME, contestAttribute.getName());
    }

    @Test
    public void testConstructor() {
        ContestAttributeInputDto input = new ContestAttributeInputDto();
        input.setName(NAME);

        ContestAttribute contestAttribute = new ContestAttribute(input);

        assertEquals(NAME, contestAttribute.getName());
    }
}