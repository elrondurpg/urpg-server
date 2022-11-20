package com.pokemonurpg.configuration.v1.gym.champion.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.champion.ChampionViews;
import com.pokemonurpg.configuration.v1.gym.champion.service.ChampionService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ChampionControllerTest {

    @InjectMocks
    private ChampionController controller;

    @Mock
    private ChampionService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(ChampionViews.Id.class, controller.getIdViewClass());
        assertEquals(ChampionViews.Brief.class, controller.getBriefViewClass());
        assertEquals(ChampionViews.Full.class, controller.getFullViewClass());
    }

}