package com.pokemonurpg.configuration.v1.gymleagues;

import com.pokemonurpg.configuration.v1.gymleagues.GymLeagueController;
import com.pokemonurpg.entities.v1.GymLeague;
import com.pokemonurpg.configuration.v1.gymleagues.GymLeagueInputDto;
import com.pokemonurpg.configuration.v1.gymleagues.GymLeagueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GymLeagueControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private GymLeagueController gymLeagueController;

    @Mock
    private GymLeagueService gymLeagueService;

    private GymLeague gymLeague = new GymLeague();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(gymLeagueService.findAllNames()).thenReturn(names);
        assertEquals(names, gymLeagueController.findAllNames());
    }

    @Test
    public void findByName() {
        when(gymLeagueService.findByName(NAME)).thenReturn(gymLeague);
        assertEquals(gymLeague, gymLeagueController.findByName(NAME));
    }

    @Test
    public void create() {
        GymLeagueInputDto input = new GymLeagueInputDto();
        input.setName(NAME);
        when(gymLeagueService.create(input)).thenReturn(gymLeague);
        assertEquals(gymLeague, gymLeagueController.create(input));
    }

    @Test
    public void update() {
        GymLeagueInputDto input = new GymLeagueInputDto();
        input.setName(NAME);
        when(gymLeagueService.update(input, DBID)).thenReturn(gymLeague);
        assertEquals(gymLeague, gymLeagueController.update(input, DBID));
    }

}