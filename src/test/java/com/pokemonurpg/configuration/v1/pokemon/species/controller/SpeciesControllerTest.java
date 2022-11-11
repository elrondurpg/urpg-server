package com.pokemonurpg.configuration.v1.pokemon.species.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.pokemon.species.SpeciesViews;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SpeciesControllerTest {

    @InjectMocks
    private SpeciesController controller;

    @Mock
    private SpeciesService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(SpeciesViews.Id.class, controller.getIdViewClass());
        assertEquals(SpeciesViews.Brief.class, controller.getBriefViewClass());
        assertEquals(SpeciesViews.Full.class, controller.getFullViewClass());
    }
}