package com.pokemonurpg.pokedex.output;

import org.junit.Test;

import static org.junit.Assert.*;

public class TypeMatchupDtoTest {
    private final static String NAME = "NAME";
    private final static Double MULTIPLIER = 2.0;

    @Test
    public void testPojo() {
        TypeMatchupDto matchup = new TypeMatchupDto(NAME, MULTIPLIER);

        assertEquals(NAME, matchup.getName());
        assertEquals(MULTIPLIER, matchup.getMultiplier());
    }

}