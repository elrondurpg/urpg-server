package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.input.EliteFourInputDto;
import com.pokemonurpg.gym.models.Badge;
import com.pokemonurpg.gym.models.EliteFour;
import com.pokemonurpg.gym.models.EliteFourOwnershipTerm;
import com.pokemonurpg.gym.repository.EliteFourRepository;
import com.pokemonurpg.species.models.Type;
import com.pokemonurpg.species.service.TypeService;
import com.pokemonurpg.stats.models.OwnedPokemon;
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
public class EliteFourServiceTest {
    private final static EliteFourOwnershipTerm TERM        = mock(EliteFourOwnershipTerm.class);
    private final static Integer                TERM_DBID   = 32742;
    private final static Integer                DBID        = 32432;
    private final static List<String>           ELITE_FOURS = new ArrayList<>();
    private final static String                 NAME        = "TEST";

    @InjectMocks
    private EliteFourService eliteFourService;

    @Mock
    private EliteFourRepository eliteFourRepository;

    @Mock
    private EliteFourOwnershipTermService eliteFourOwnershipTermService;

    @Mock
    private EliteFourPokemonService eliteFourPokemonService;

    @Mock
    private TypeService typeService;

    private EliteFour eliteFour = new EliteFour();

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        when(eliteFourRepository.findAllNames()).thenReturn(ELITE_FOURS);
        assertEquals(ELITE_FOURS, eliteFourService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(eliteFourRepository.findByDbid(DBID)).thenReturn(eliteFour);
        assertEquals(eliteFour, eliteFourService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(eliteFourRepository.findByName(NAME)).thenReturn(eliteFour);
        assertEquals(eliteFour, eliteFourService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(eliteFourRepository.findByName(NAME)).thenReturn(null);
        when(eliteFourRepository.findFirstByNameStartingWith(NAME)).thenReturn(eliteFour);
        assertEquals(eliteFour, eliteFourService.findByName(NAME));
    }

    @Test
    public void findByCurrentOwnershipTerm() {
        when(eliteFourRepository.findByCurrentOwnerRecord(TERM)).thenReturn(eliteFour);
        assertEquals(eliteFour, eliteFourService.findByCurrentOwnerRecord(TERM));
    }

    @Test
    public void create() {
        EliteFourInputDto input = new EliteFourInputDto();
        input.setName(NAME);

        EliteFour eliteFour = eliteFourService.create(input);
        assertEquals(NAME, eliteFour.getName());
        verify(eliteFourRepository, times(1)).save(eliteFour);
        verify(eliteFourPokemonService, times(1)).updateAll(input, eliteFour);
    }

    @Test
    public void updateExistingRecord() {
        EliteFourInputDto input = new EliteFourInputDto();
        input.setName(NAME);
        input.setCurrentOwnerRecordDbid(TERM_DBID);

        when(eliteFourRepository.findByDbid(DBID)).thenReturn(eliteFour);
        when(eliteFourOwnershipTermService.findByDbid(TERM_DBID)).thenReturn(TERM);

        EliteFour eliteFour1 = eliteFourService.update(input, DBID);
        assertEquals(eliteFour, eliteFour1);
        assertEquals(NAME, eliteFour1.getName());
        assertEquals(TERM, eliteFour1.getCurrentOwnerRecord());
        verify(eliteFourRepository, times(1)).save(eliteFour1);
        verify(eliteFourPokemonService, times(1)).updateAll(input, eliteFour1);
    }

    @Test
    public void updateNonExistingRecord() {
        EliteFourInputDto input = new EliteFourInputDto();
        input.setName(NAME);

        when(eliteFourRepository.findByDbid(DBID)).thenReturn(null);

        EliteFour eliteFour1 = eliteFourService.update(input, DBID);
        assertNull(eliteFour1);
        verify(eliteFourRepository, times(0)).save(Matchers.any());
    }

    @Test
    public void updateCurrentOwnershipRecord() {
        eliteFourService.updateCurrentOwnerRecord(eliteFour, TERM);
        assertEquals(TERM, eliteFour.getCurrentOwnerRecord());
        verify(eliteFourRepository, times(1)).save(eliteFour);
    }

    @Test
    public void delete() {
        eliteFourService.delete(DBID);
        verify(eliteFourRepository, times(1)).deleteByDbid(captor.capture());
        assertEquals(DBID, captor.getValue());
    }

    @Test
    public void updateEmbeddedValues_SetsOwnerRecordToNull_whenInputOwnerRecordIsNullAndRemoveOwnerIsTrue() {
        EliteFourInputDto input = new EliteFourInputDto();
        input.setRemoveOwner(true);

        EliteFour eliteFour = new EliteFour();
        eliteFour.setCurrentOwnerRecord(TERM);
        Set<OwnedPokemon> pokemon = new HashSet<>();
        pokemon.add(new OwnedPokemon());
        eliteFour.setPokemon(pokemon);

        eliteFourService.updateEmbeddedValues(input, eliteFour);
        assertNull(eliteFour.getCurrentOwnerRecord());
        assertTrue(eliteFour.getPokemon().isEmpty());
    }

}