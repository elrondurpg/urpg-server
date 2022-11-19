package com.pokemonurpg.configuration.v1.gym.championownershipterm.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.championownershipterm.ChampionOwnershipTermViews;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.service.ChampionOwnershipTermService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ChampionOwnershipTermControllerTest {

    @InjectMocks
    private ChampionOwnershipTermController controller;

    @Mock
    private ChampionOwnershipTermService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(ChampionOwnershipTermViews.Id.class, controller.getIdViewClass());
        assertEquals(ChampionOwnershipTermViews.Brief.class, controller.getBriefViewClass());
        assertEquals(ChampionOwnershipTermViews.Brief.class, controller.getFullViewClass());
    }

}