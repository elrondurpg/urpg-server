package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.entities.v1.Type;
import com.pokemonurpg.pokedex.v1.TypeMatchupDto;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class TypeMatchupDtoTest {
    private final static Type TYPE = mock(Type.class);
    private final static Double MULTIPLIER = 2.0;

    @Test
    public void testPojo() {
        TypeMatchupDto matchup = new TypeMatchupDto(TYPE, MULTIPLIER);

        assertEquals(TYPE, matchup.getType());
        assertEquals(MULTIPLIER, matchup.getMultiplier());
    }

}