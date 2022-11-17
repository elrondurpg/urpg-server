package com.pokemonurpg.configuration.v1.pokemon.nature.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.pokemon.nature.NatureViews;
import com.pokemonurpg.configuration.v1.pokemon.nature.service.NatureService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class NatureControllerTest {

    @InjectMocks
    private NatureController controller;

    @Mock
    private NatureService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(NatureViews.Id.class, controller.getIdViewClass());
        assertEquals(NatureViews.Id.class, controller.getBriefViewClass());
        assertEquals(NatureViews.Id.class, controller.getFullViewClass());
    }

}