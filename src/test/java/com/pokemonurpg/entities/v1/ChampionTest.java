package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.championslots.ChampionInputDto;
import com.pokemonurpg.entities.v1.Champion;
import com.pokemonurpg.entities.v1.ChampionOwnershipTerm;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ChampionTest {
    private final static ChampionOwnershipTerm OWNER_REC   = mock(ChampionOwnershipTerm.class);
    private final static Integer                    DBID        = 32432;
    private final static String                     NAME        = "NAME";
    private final static Set<OwnedPokemon>          POKEMON     = new HashSet<>();

    @Test
    public void testPojo() {
        Champion champion = new Champion();
        champion.setDbid(DBID);
        champion.setName(NAME);
        champion.setPokemon(POKEMON);
        champion.setCurrentOwnerRecord(OWNER_REC);

        assertEquals(DBID, champion.getDbid());
        assertEquals(NAME, champion.getName());
        assertEquals(POKEMON, champion.getPokemon());
        assertEquals(OWNER_REC, champion.getCurrentOwnerRecord());
    }

    @Test
    public void testConstructor() {
        ChampionInputDto input = new ChampionInputDto();
        input.setName(NAME);

        Champion champion = new Champion(input);
        assertEquals(NAME, champion.getName());
    }

    @Test
    public void testConstructorWithDefaultValues() {
        ChampionInputDto input = new ChampionInputDto();
        input.setName(NAME);

        Champion champion = new Champion(input);
        assertEquals(NAME, champion.getName());
    }


}