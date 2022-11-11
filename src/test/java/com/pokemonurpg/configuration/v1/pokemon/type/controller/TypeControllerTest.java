package com.pokemonurpg.configuration.v1.pokemon.type.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.pokemon.type.TypeViews;
import com.pokemonurpg.configuration.v1.pokemon.type.service.TypeService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TypeControllerTest {

    @InjectMocks
    private TypeController controller;

    @Mock
    private TypeService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(TypeViews.Id.class, controller.getIdViewClass());
        assertEquals(TypeViews.Id.class, controller.getBriefViewClass());
        assertEquals(TypeViews.Id.class, controller.getFullViewClass());
    }

}