package com.pokemonurpg.configuration.v1.pokemon.capturemethod.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.pokemon.capturemethod.CaptureMethodViews;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.service.CaptureMethodService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CaptureMethodControllerTest {

    @InjectMocks
    private CaptureMethodController controller;

    @Mock
    private CaptureMethodService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(CaptureMethodViews.Id.class, controller.getIdViewClass());
        assertEquals(CaptureMethodViews.Id.class, controller.getBriefViewClass());
        assertEquals(CaptureMethodViews.Id.class, controller.getFullViewClass());
    }

}