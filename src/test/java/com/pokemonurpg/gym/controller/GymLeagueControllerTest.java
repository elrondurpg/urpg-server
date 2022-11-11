package com.pokemonurpg.gym.controller;

import com.pokemonurpg.gym.models.GymLeague;
import com.pokemonurpg.gym.input.GymLeagueInputDto;
import com.pokemonurpg.gym.service.GymLeagueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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