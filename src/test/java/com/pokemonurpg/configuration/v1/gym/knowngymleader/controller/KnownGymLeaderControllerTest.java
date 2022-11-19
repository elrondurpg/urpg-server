package com.pokemonurpg.configuration.v1.gym.knowngymleader.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.knowngymleader.KnownGymLeaderViews;
import com.pokemonurpg.configuration.v1.gym.knowngymleader.service.KnownGymLeaderService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class KnownGymLeaderControllerTest {

    @InjectMocks
    private KnownGymLeaderController controller;

    @Mock
    private KnownGymLeaderService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(KnownGymLeaderViews.Id.class, controller.getIdViewClass());
        assertEquals(KnownGymLeaderViews.Id.class, controller.getBriefViewClass());
        assertEquals(KnownGymLeaderViews.Id.class, controller.getFullViewClass());
    }

}