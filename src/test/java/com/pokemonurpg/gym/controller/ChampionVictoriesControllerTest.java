package com.pokemonurpg.gym.controller;

import com.pokemonurpg.stats.models.ChampionVictory;
import com.pokemonurpg.stats.service.ChampionVictoryService;
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