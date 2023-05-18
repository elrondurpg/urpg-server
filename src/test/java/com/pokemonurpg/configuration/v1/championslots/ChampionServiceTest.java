package com.pokemonurpg.configuration.v1.championslots;

import com.pokemonurpg.entities.v1.Champion;
import com.pokemonurpg.entities.v1.ChampionOwnershipTerm;
import com.pokemonurpg.infrastructure.v1.data.jpa.ChampionOwnershipTermRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.ChampionRepository;
import com.pokemonurpg.configuration.v1.championrecords.ChampionOwnershipTermService;
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
public class ChampionServiceTest {
    private final static ChampionOwnershipTerm  TERM        = mock(ChampionOwnershipTerm.class);
    private final static Integer                TERM_DBID   = 32742;
    private final static Integer                DBID        = 32432;
    private final static List<String>           CHAMPIONS = new ArrayList<>();
    private final static String                 NAME        = "TEST";

    @InjectMocks
    private ChampionService championService;

    @Mock
    private ChampionRepository championRepository;

    @Mock
    private ChampionOwnershipTermRepository championRecordRepository;

    @Mock
    private ChampionPokemonService championPokemonService;

    private Champion champion = new Champion();

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        when(championRepository.findAllNames()).thenReturn(CHAMPIONS);
        assertEquals(CHAMPIONS, championService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(championRepository.findByDbid(DBID)).thenReturn(champion);
        assertEquals(champion, championService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(championRepository.findByName(NAME)).thenReturn(champion);
        assertEquals(champion, championService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(championRepository.findByName(NAME)).thenReturn(null);
        when(championRepository.findFirstByNameStartingWith(NAME)).thenReturn(champion);
        assertEquals(champion, championService.findByName(NAME));
    }

    @Test
    public void findByCurrentOwnershipTerm() {
        when(championRepository.findByCurrentOwnerRecord(TERM)).thenReturn(champion);
        assertEquals(champion, championService.findByCurrentOwnerRecord(TERM));
    }

    @Test
    public void create() {
        ChampionInputDto input = new ChampionInputDto();
        input.setName(NAME);

        Champion champion = championService.create(input);
        assertEquals(NAME, champion.getName());
        verify(championRepository, times(1)).save(champion);
        verify(championPokemonService, times(1)).updateAll(input, champion);
    }

    @Test
    public void updateExistingRecord() {
        ChampionInputDto input = new ChampionInputDto();
        input.setName(NAME);
        input.setCurrentOwnerRecordDbid(TERM_DBID);

        when(championRepository.findByDbid(DBID)).thenReturn(champion);
        when(championRecordRepository.findByDbid(TERM_DBID)).thenReturn(TERM);

        Champion champion1 = championService.update(input, DBID);
        assertEquals(champion, champion1);
        assertEquals(NAME, champion1.getName());
        assertEquals(TERM, champion1.getCurrentOwnerRecord());
        verify(championRepository, times(1)).save(champion1);
        verify(championPokemonService, times(1)).updateAll(input, champion1);
    }

    @Test
    public void updateNonExistingRecord() {
        ChampionInputDto input = new ChampionInputDto();
        input.setName(NAME);

        when(championRepository.findByDbid(DBID)).thenReturn(null);

        Champion champion1 = championService.update(input, DBID);
        assertNull(champion1);
    }

    @Test
    public void updateCurrentOwnershipRecord() {
        championService.updateCurrentOwnerRecord(champion, TERM);
        assertEquals(TERM, champion.getCurrentOwnerRecord());
        verify(championRepository, times(1)).save(champion);
    }

    @Test
    public void delete() {
        championService.delete(DBID);
        verify(championRepository, times(1)).deleteByDbid(captor.capture());
        assertEquals(DBID, captor.getValue());
    }

    @Test
    public void updateEmbeddedValues_SetsOwnerRecordToNull_whenInputOwnerRecordIsNullAndRemoveOwnerIsTrue() {
        ChampionInputDto input = new ChampionInputDto();
        input.setRemoveOwner(true);

        Champion champion = new Champion();
        champion.setCurrentOwnerRecord(TERM);
        Set<OwnedPokemon> pokemon = new HashSet<>();
        pokemon.add(new OwnedPokemon());
        champion.setPokemon(pokemon);

        championService.updateEmbeddedValues(input, champion);
        assertNull(champion.getCurrentOwnerRecord());
        assertTrue(champion.getPokemon().isEmpty());
    }

}