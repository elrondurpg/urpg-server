package com.pokemonurpg.stats.models;

import com.pokemonurpg.contest.models.ContestAttribute;
import com.pokemonurpg.contest.models.ContestRank;
import com.pokemonurpg.stats.input.EarnedRibbonInputDto;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class EarnedRibbonTest {
    private final static Integer DBID = 3432;
    private final static OwnedPokemon POKEMON = mock(OwnedPokemon.class);
    private final static ContestRank RANK = mock(ContestRank.class);
    private final static ContestAttribute ATTRIBUTE = mock(ContestAttribute.class);
    private final static String URL = "URL";
    private final static Boolean SPENT = true;

    @Test
    public void testPojo() {
        EarnedRibbon ribbon = new EarnedRibbon();
    }

    @Test
    public void testConstructor() {
        EarnedRibbonInputDto input = new EarnedRibbonInputDto();
        /*input.setUrl(URL);
        input.setSpent(SPENT);

        EarnedRibbon ribbon = new EarnedRibbon(input, POKEMON, RANK, ATTRIBUTE, null);
        assertEquals(POKEMON, ribbon.getPokemon());
        assertEquals(RANK, ribbon.getRank());
        assertEquals(ATTRIBUTE, ribbon.getAttribute());
        assertEquals(URL, ribbon.getUrl());
        assertEquals(SPENT, ribbon.getSpent());*/
    }

}