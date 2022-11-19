package com.pokemonurpg.configuration.v1.gym.knownchampion.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.knownchampion.KnownChampionViews;
import com.pokemonurpg.configuration.v1.gym.knownchampion.service.KnownChampionService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class KnownChampionControllerTest {

    @InjectMocks
    private KnownChampionController controller;

    @Mock
    private KnownChampionService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(KnownChampionViews.Id.class, controller.getIdViewClass());
        assertEquals(KnownChampionViews.Id.class, controller.getBriefViewClass());
        assertEquals(KnownChampionViews.Id.class, controller.getFullViewClass());
    }

}