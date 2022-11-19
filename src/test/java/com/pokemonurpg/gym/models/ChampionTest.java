package com.pokemonurpg.gym.models;

import com.pokemonurpg.configuration.v1.gym.championownershipterm.model.ChampionOwnershipTerm;
import com.pokemonurpg.gym.input.ChampionInputDto;
import com.pokemonurpg.stats.models.OwnedPokemon;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ChampionTest {
    private final static ChampionOwnershipTerm      OWNER_REC   = mock(ChampionOwnershipTerm.class);
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