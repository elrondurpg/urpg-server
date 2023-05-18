package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.championslots.ChampionSlotRequest;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ChampionSlotTest {
    private final static ChampionRecord OWNER_REC   = mock(ChampionRecord.class);
    private final static Integer                    DBID        = 32432;
    private final static String                     NAME        = "NAME";
    private final static Set<OwnedPokemon>          POKEMON     = new HashSet<>();

    @Test
    public void testPojo() {
        ChampionSlot championSlot = new ChampionSlot();
        championSlot.setDbid(DBID);
        championSlot.setName(NAME);
        championSlot.setPokemon(POKEMON);
        championSlot.setCurrentOwnerRecord(OWNER_REC);

        assertEquals(DBID, championSlot.getDbid());
        assertEquals(NAME, championSlot.getName());
        assertEquals(POKEMON, championSlot.getPokemon());
        assertEquals(OWNER_REC, championSlot.getCurrentOwnerRecord());
    }

    @Test
    public void testConstructor() {
        ChampionSlotRequest input = new ChampionSlotRequest();
        input.setName(NAME);

        ChampionSlot championSlot = new ChampionSlot(input);
        assertEquals(NAME, championSlot.getName());
    }

    @Test
    public void testConstructorWithDefaultValues() {
        ChampionSlotRequest input = new ChampionSlotRequest();
        input.setName(NAME);

        ChampionSlot championSlot = new ChampionSlot(input);
        assertEquals(NAME, championSlot.getName());
    }


}