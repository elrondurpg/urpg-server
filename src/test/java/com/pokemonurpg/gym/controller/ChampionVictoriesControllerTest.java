package com.pokemonurpg.gym.controller;

import com.pokemonurpg.stats.models.ChampionVictory;
import com.pokemonurpg.stats.service.ChampionVictoryService;
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
public class ChampionVictoriesControllerTest {
    private final static List<ChampionVictory> VICTORIES = new ArrayList<>();

    @InjectMocks
    private ChampionVictoriesController championVictoriesController;

    @Mock
    private ChampionVictoryService championVictoryService;

    @Test
    public void findAll() {
        when(championVictoryService.findAll()).thenReturn(VICTORIES);
        assertEquals(VICTORIES, championVictoriesController.findAll());
    }

}