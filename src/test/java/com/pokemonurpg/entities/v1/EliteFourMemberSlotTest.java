package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourMemberSlotRequest;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class EliteFourMemberSlotTest {
    private final static EliteFourMemberRecord OWNER_REC   = mock(EliteFourMemberRecord.class);
    private final static Integer                    DBID        = 32432;
    private final static String                     NAME        = "NAME";
    private final static Set<OwnedPokemon>          POKEMON     = new HashSet<>();

    @Test
    public void testPojo() {
        EliteFourMemberSlot eliteFourMemberSlot = new EliteFourMemberSlot();
        eliteFourMemberSlot.setDbid(DBID);
        eliteFourMemberSlot.setName(NAME);
        eliteFourMemberSlot.setPokemon(POKEMON);
        eliteFourMemberSlot.setCurrentOwnerRecord(OWNER_REC);

        assertEquals(DBID, eliteFourMemberSlot.getDbid());
        assertEquals(NAME, eliteFourMemberSlot.getName());
        assertEquals(POKEMON, eliteFourMemberSlot.getPokemon());
        assertEquals(OWNER_REC, eliteFourMemberSlot.getCurrentOwnerRecord());
    }

    @Test
    public void testConstructor() {
        EliteFourMemberSlotRequest input = new EliteFourMemberSlotRequest();
        input.setName(NAME);

        EliteFourMemberSlot eliteFourMemberSlot = new EliteFourMemberSlot(input);
        assertEquals(NAME, eliteFourMemberSlot.getName());
    }

    @Test
    public void testConstructorWithDefaultValues() {
        EliteFourMemberSlotRequest input = new EliteFourMemberSlotRequest();
        input.setName(NAME);

        EliteFourMemberSlot eliteFourMemberSlot = new EliteFourMemberSlot(input);
        assertEquals(NAME, eliteFourMemberSlot.getName());
    }


}