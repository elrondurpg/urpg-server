package com.pokemonurpg.configuration.v1.gym.badge.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.badge.BadgeViews;
import com.pokemonurpg.configuration.v1.gym.badge.service.BadgeService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BadgeControllerTest {

    @InjectMocks
    private BadgeController controller;

    @Mock
    private BadgeService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(BadgeViews.Id.class, controller.getIdViewClass());
        assertEquals(BadgeViews.Id.class, controller.getBriefViewClass());
        assertEquals(BadgeViews.Id.class, controller.getFullViewClass());
    }

}