package com.pokemonurpg.configuration.v1.abilities;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbilityRequestTest {
    private final static String NAME = "ABILITY";
    private final static String DESCRIPTION = "THIS IS A TEST";

    private AbilityRequest abilityRequest = new AbilityRequest();

    @Test
    public void testPojo() {
        abilityRequest.setName(NAME);
        abilityRequest.setDescription(DESCRIPTION);

        assertEquals(NAME, abilityRequest.getName());
        assertEquals(DESCRIPTION, abilityRequest.getDescription());
    }

}