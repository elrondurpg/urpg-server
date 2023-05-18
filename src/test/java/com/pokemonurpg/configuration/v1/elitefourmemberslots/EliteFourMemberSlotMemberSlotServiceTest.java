package com.pokemonurpg.configuration.v1.elitefourmemberslots;

import com.pokemonurpg.entities.v1.EliteFourMemberSlot;
import com.pokemonurpg.entities.v1.EliteFourMemberRecord;
import com.pokemonurpg.infrastructure.v1.data.jpa.EliteFourMemberRecordRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.EliteFourMemberSlotRepository;
import com.pokemonurpg.configuration.v1.types.TypeService;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EliteFourMemberSlotMemberSlotServiceTest {
    private final static EliteFourMemberRecord TERM        = mock(EliteFourMemberRecord.class);
    private final static Integer                TERM_DBID   = 32742;
    private final static Integer                DBID        = 32432;
    private final static List<String>           ELITE_FOURS = new ArrayList<>();
    private final static String                 NAME        = "TEST";

    @InjectMocks
    private EliteFourMemberSlotService eliteFourMemberSlotService;

    @Mock
    private EliteFourMemberSlotRepository eliteFourMemberSlotRepository;

    @Mock
    private EliteFourMemberRecordRepository eliteFourMemberRecordRepository;

    @Mock
    private EliteFourPokemonService eliteFourPokemonService;

    @Mock
    private TypeService typeService;

    private EliteFourMemberSlot eliteFourMemberSlot = new EliteFourMemberSlot();

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        when(eliteFourMemberSlotRepository.findAllNames()).thenReturn(ELITE_FOURS);
        assertEquals(ELITE_FOURS, eliteFourMemberSlotService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(eliteFourMemberSlotRepository.findByDbid(DBID)).thenReturn(eliteFourMemberSlot);
        assertEquals(eliteFourMemberSlot, eliteFourMemberSlotService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(eliteFourMemberSlotRepository.findByName(NAME)).thenReturn(eliteFourMemberSlot);
        assertEquals(eliteFourMemberSlot, eliteFourMemberSlotService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(eliteFourMemberSlotRepository.findByName(NAME)).thenReturn(null);
        when(eliteFourMemberSlotRepository.findFirstByNameStartingWith(NAME)).thenReturn(eliteFourMemberSlot);
        assertEquals(eliteFourMemberSlot, eliteFourMemberSlotService.findByName(NAME));
    }

    @Test
    public void findByCurrentOwnershipTerm() {
        when(eliteFourMemberSlotRepository.findByCurrentOwnerRecord(TERM)).thenReturn(eliteFourMemberSlot);
        assertEquals(eliteFourMemberSlot, eliteFourMemberSlotService.findByCurrentOwnerRecord(TERM));
    }

    @Test
    public void create() {
        EliteFourMemberSlotRequest input = new EliteFourMemberSlotRequest();
        input.setName(NAME);

        EliteFourMemberSlot eliteFourMemberSlot = eliteFourMemberSlotService.create(input);
        assertEquals(NAME, eliteFourMemberSlot.getName());
        verify(eliteFourMemberSlotRepository, times(1)).save(eliteFourMemberSlot);
        verify(eliteFourPokemonService, times(1)).updateAll(input, eliteFourMemberSlot);
    }

    @Test
    public void updateExistingRecord() {
        EliteFourMemberSlotRequest input = new EliteFourMemberSlotRequest();
        input.setName(NAME);
        input.setCurrentOwnerRecordDbid(TERM_DBID);

        when(eliteFourMemberSlotRepository.findByDbid(DBID)).thenReturn(eliteFourMemberSlot);
        when(eliteFourMemberRecordRepository.findByDbid(TERM_DBID)).thenReturn(TERM);

        EliteFourMemberSlot eliteFourMemberSlot1 = eliteFourMemberSlotService.update(input, DBID);
        assertEquals(eliteFourMemberSlot, eliteFourMemberSlot1);
        assertEquals(NAME, eliteFourMemberSlot1.getName());
        assertEquals(TERM, eliteFourMemberSlot1.getCurrentOwnerRecord());
        verify(eliteFourMemberSlotRepository, times(1)).save(eliteFourMemberSlot1);
        verify(eliteFourPokemonService, times(1)).updateAll(input, eliteFourMemberSlot1);
    }

    @Test
    public void updateNonExistingRecord() {
        EliteFourMemberSlotRequest input = new EliteFourMemberSlotRequest();
        input.setName(NAME);

        when(eliteFourMemberSlotRepository.findByDbid(DBID)).thenReturn(null);

        EliteFourMemberSlot eliteFourMemberSlot1 = eliteFourMemberSlotService.update(input, DBID);
        assertNull(eliteFourMemberSlot1);
    }

    @Test
    public void updateCurrentOwnershipRecord() {
        eliteFourMemberSlotService.updateCurrentOwnerRecord(eliteFourMemberSlot, TERM);
        assertEquals(TERM, eliteFourMemberSlot.getCurrentOwnerRecord());
        verify(eliteFourMemberSlotRepository, times(1)).save(eliteFourMemberSlot);
    }

    @Test
    public void delete() {
        eliteFourMemberSlotService.delete(DBID);
        verify(eliteFourMemberSlotRepository, times(1)).deleteByDbid(captor.capture());
        assertEquals(DBID, captor.getValue());
    }

    @Test
    public void updateEmbeddedValues_SetsOwnerRecordToNull_whenInputOwnerRecordIsNullAndRemoveOwnerIsTrue() {
        EliteFourMemberSlotRequest input = new EliteFourMemberSlotRequest();
        input.setRemoveOwner(true);

        EliteFourMemberSlot eliteFourMemberSlot = new EliteFourMemberSlot();
        eliteFourMemberSlot.setCurrentOwnerRecord(TERM);
        Set<OwnedPokemon> pokemon = new HashSet<>();
        pokemon.add(new OwnedPokemon());
        eliteFourMemberSlot.setPokemon(pokemon);

        eliteFourMemberSlotService.updateEmbeddedValues(input, eliteFourMemberSlot);
        assertNull(eliteFourMemberSlot.getCurrentOwnerRecord());
        assertTrue(eliteFourMemberSlot.getPokemon().isEmpty());
    }

}