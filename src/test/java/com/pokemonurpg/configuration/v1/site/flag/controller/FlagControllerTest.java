package com.pokemonurpg.configuration.v1.site.flag.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.site.flag.FlagViews;
import com.pokemonurpg.configuration.v1.site.flag.service.FlagService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FlagControllerTest {

    @InjectMocks
    private FlagController controller;

    @Mock
    private FlagService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(FlagViews.Id.class, controller.getIdViewClass());
        assertEquals(FlagViews.Id.class, controller.getBriefViewClass());
        assertEquals(FlagViews.Id.class, controller.getFullViewClass());
    }

}