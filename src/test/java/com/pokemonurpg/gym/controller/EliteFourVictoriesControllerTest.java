package com.pokemonurpg.gym.controller;

import com.pokemonurpg.stats.models.EliteFourVictory;
import com.pokemonurpg.stats.service.EliteFourVictoryService;
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
public class EliteFourVictoriesControllerTest {
    private final static List<EliteFourVictory> VICTORIES = new ArrayList<>();

    @InjectMocks
    private EliteFourVictoriesController eliteFourVictoriesController;

    @Mock
    private EliteFourVictoryService eliteFourVictoryService;

    @Test
    public void findAll() {
        when(eliteFourVictoryService.findAll()).thenReturn(VICTORIES);
        assertEquals(VICTORIES, eliteFourVictoriesController.findAll());
    }

}