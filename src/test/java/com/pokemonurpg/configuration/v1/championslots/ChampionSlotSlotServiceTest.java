package com.pokemonurpg.configuration.v1.championslots;

import com.pokemonurpg.entities.v1.ChampionSlot;
import com.pokemonurpg.entities.v1.ChampionRecord;
import com.pokemonurpg.infrastructure.v1.data.jpa.ChampionRecordRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.ChampionSlotRepository;
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
public class ChampionSlotSlotServiceTest {
    private final static ChampionRecord TERM        = mock(ChampionRecord.class);
    private final static Integer                TERM_DBID   = 32742;
    private final static Integer                DBID        = 32432;
    private final static List<String>           CHAMPIONS = new ArrayList<>();
    private final static String                 NAME        = "TEST";

    @InjectMocks
    private ChampionSlotService championSlotService;

    @Mock
    private ChampionSlotRepository championSlotRepository;

    @Mock
    private ChampionRecordRepository championRecordRepository;

    @Mock
    private ChampionPokemonService championPokemonService;

    private ChampionSlot championSlot = new ChampionSlot();

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        when(championSlotRepository.findAllNames()).thenReturn(CHAMPIONS);
        assertEquals(CHAMPIONS, championSlotService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(championSlotRepository.findByDbid(DBID)).thenReturn(championSlot);
        assertEquals(championSlot, championSlotService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(championSlotRepository.findByName(NAME)).thenReturn(championSlot);
        assertEquals(championSlot, championSlotService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(championSlotRepository.findByName(NAME)).thenReturn(null);
        when(championSlotRepository.findFirstByNameStartingWith(NAME)).thenReturn(championSlot);
        assertEquals(championSlot, championSlotService.findByName(NAME));
    }

    @Test
    public void findByCurrentOwnershipTerm() {
        when(championSlotRepository.findByCurrentOwnerRecord(TERM)).thenReturn(championSlot);
        assertEquals(championSlot, championSlotService.findByCurrentOwnerRecord(TERM));
    }

    @Test
    public void create() {
        ChampionSlotRequest input = new ChampionSlotRequest();
        input.setName(NAME);

        ChampionSlot championSlot = championSlotService.create(input);
        assertEquals(NAME, championSlot.getName());
        verify(championSlotRepository, times(1)).save(championSlot);
        verify(championPokemonService, times(1)).updateAll(input, championSlot);
    }

    @Test
    public void updateExistingRecord() {
        ChampionSlotRequest input = new ChampionSlotRequest();
        input.setName(NAME);
        input.setCurrentOwnerRecordDbid(TERM_DBID);

        when(championSlotRepository.findByDbid(DBID)).thenReturn(championSlot);
        when(championRecordRepository.findByDbid(TERM_DBID)).thenReturn(TERM);

        ChampionSlot championSlot1 = championSlotService.update(input, DBID);
        assertEquals(championSlot, championSlot1);
        assertEquals(NAME, championSlot1.getName());
        assertEquals(TERM, championSlot1.getCurrentOwnerRecord());
        verify(championSlotRepository, times(1)).save(championSlot1);
        verify(championPokemonService, times(1)).updateAll(input, championSlot1);
    }

    @Test
    public void updateNonExistingRecord() {
        ChampionSlotRequest input = new ChampionSlotRequest();
        input.setName(NAME);

        when(championSlotRepository.findByDbid(DBID)).thenReturn(null);

        ChampionSlot championSlot1 = championSlotService.update(input, DBID);
        assertNull(championSlot1);
    }

    @Test
    public void updateCurrentOwnershipRecord() {
        championSlotService.updateCurrentOwnerRecord(championSlot, TERM);
        assertEquals(TERM, championSlot.getCurrentOwnerRecord());
        verify(championSlotRepository, times(1)).save(championSlot);
    }

    @Test
    public void delete() {
        championSlotService.delete(DBID);
        verify(championSlotRepository, times(1)).deleteByDbid(captor.capture());
        assertEquals(DBID, captor.getValue());
    }

    @Test
    public void updateEmbeddedValues_SetsOwnerRecordToNull_whenInputOwnerRecordIsNullAndRemoveOwnerIsTrue() {
        ChampionSlotRequest input = new ChampionSlotRequest();
        input.setRemoveOwner(true);

        ChampionSlot championSlot = new ChampionSlot();
        championSlot.setCurrentOwnerRecord(TERM);
        Set<OwnedPokemon> pokemon = new HashSet<>();
        pokemon.add(new OwnedPokemon());
        championSlot.setPokemon(pokemon);

        championSlotService.updateEmbeddedValues(input, championSlot);
        assertNull(championSlot.getCurrentOwnerRecord());
        assertTrue(championSlot.getPokemon().isEmpty());
    }

}