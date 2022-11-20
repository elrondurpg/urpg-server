package com.pokemonurpg.configuration.v1.gym.league.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.league.LeagueViews;
import com.pokemonurpg.configuration.v1.gym.league.service.LeagueService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LeagueControllerTest {

    @InjectMocks
    private LeagueController controller;

    @Mock
    private LeagueService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(LeagueViews.Id.class, controller.getIdViewClass());
        assertEquals(LeagueViews.Id.class, controller.getBriefViewClass());
        assertEquals(LeagueViews.Id.class, controller.getFullViewClass());
    }

}