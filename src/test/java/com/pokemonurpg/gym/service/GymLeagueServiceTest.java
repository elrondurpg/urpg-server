package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.input.GymLeagueInputDto;
import com.pokemonurpg.gym.models.GymLeague;
import com.pokemonurpg.gym.repository.GymLeagueRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GymLeagueServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private GymLeagueService gymLeagueService;

    @Mock
    private GymLeagueRepository gymLeagueRepository;

    private GymLeague gymLeague = new GymLeague();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(gymLeagueRepository.findAllNames()).thenReturn(types);

        assertEquals(types, gymLeagueService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(gymLeagueRepository.findByDbid(DBID)).thenReturn(gymLeague);
        assertEquals(gymLeague, gymLeagueService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(gymLeagueRepository.findByName(NAME)).thenReturn(gymLeague);
        assertEquals(gymLeague, gymLeagueService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(gymLeagueRepository.findByName(NAME)).thenReturn(null);
        when(gymLeagueRepository.findFirstByNameStartingWith(NAME)).thenReturn(gymLeague);
        assertEquals(gymLeague, gymLeagueService.findByName(NAME));
    }

    @Test
    public void create() {
        GymLeagueInputDto input = new GymLeagueInputDto();
        input.setName(NAME);

        GymLeague gymLeague = gymLeagueService.create(input);
        assertEquals(NAME, gymLeague.getName());
        verify(gymLeagueRepository, times(1)).save(gymLeague);
    }

    @Test
    public void updateExistingRecord() {
        GymLeagueInputDto input = new GymLeagueInputDto();
        input.setName(NAME);

        when(gymLeagueRepository.findByDbid(DBID)).thenReturn(gymLeague);

        GymLeague gymLeague1 = gymLeagueService.update(input, DBID);
        assertEquals(gymLeague, gymLeague1);
        assertEquals(NAME, gymLeague1.getName());
        verify(gymLeagueRepository, times(1)).save(gymLeague1);
    }

    @Test
    public void updateNonExistingRecord() {
        GymLeagueInputDto input = new GymLeagueInputDto();
        input.setName(NAME);

        when(gymLeagueRepository.findByDbid(DBID)).thenReturn(null);

        GymLeague gymLeague1 = gymLeagueService.update(input, DBID);
        assertNull(gymLeague1);
        verify(gymLeagueRepository, times(0)).save(Matchers.any());
    }

}