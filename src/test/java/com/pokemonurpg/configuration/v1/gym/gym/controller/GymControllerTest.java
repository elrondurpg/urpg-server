package com.pokemonurpg.configuration.v1.gym.gym.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.gym.GymViews;
import com.pokemonurpg.configuration.v1.gym.gym.service.GymService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GymControllerTest {

    @InjectMocks
    private GymController controller;

    @Mock
    private GymService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(GymViews.Id.class, controller.getIdViewClass());
        assertEquals(GymViews.Brief.class, controller.getBriefViewClass());
        assertEquals(GymViews.Full.class, controller.getFullViewClass());
    }

}