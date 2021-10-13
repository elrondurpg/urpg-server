package com.pokemonurpg.gym.models;

import com.pokemonurpg.gym.input.EliteFourInputDto;
import com.pokemonurpg.species.models.Type;
import com.pokemonurpg.stats.models.EliteFourVictory;
import com.pokemonurpg.stats.models.OwnedPokemon;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class EliteFourTest {
    private final static EliteFourOwnershipTerm     OWNER_REC   = mock(EliteFourOwnershipTerm.class);
    private final static Integer                    DBID        = 32432;
    private final static String                     NAME        = "NAME";
    private final static Set<OwnedPokemon>          POKEMON     = new HashSet<>();

    @Test
    public void testPojo() {
        EliteFour eliteFour = new EliteFour();
        eliteFour.setDbid(DBID);
        eliteFour.setName(NAME);
        eliteFour.setPokemon(POKEMON);
        eliteFour.setCurrentOwnerRecord(OWNER_REC);

        assertEquals(DBID, eliteFour.getDbid());
        assertEquals(NAME, eliteFour.getName());
        assertEquals(POKEMON, eliteFour.getPokemon());
        assertEquals(OWNER_REC, eliteFour.getCurrentOwnerRecord());
    }

    @Test
    public void testConstructor() {
        EliteFourInputDto input = new EliteFourInputDto();
        input.setName(NAME);

        EliteFour eliteFour = new EliteFour(input);
        assertEquals(NAME, eliteFour.getName());
    }

    @Test
    public void testConstructorWithDefaultValues() {
        EliteFourInputDto input = new EliteFourInputDto();
        input.setName(NAME);

        EliteFour eliteFour = new EliteFour(input);
        assertEquals(NAME, eliteFour.getName());
    }


}