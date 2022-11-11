package com.pokemonurpg.configuration.v1.pokemon.ability.controller;

import com.pokemonurpg.configuration.v1.pokemon.ability.AbilityViews;
import com.pokemonurpg.configuration.v1.pokemon.ability.service.AbilityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AbilityControllerTest {

    @InjectMocks
    private AbilityController controller;

    @Mock
    private AbilityService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(AbilityViews.Id.class, controller.getIdViewClass());
        assertEquals(AbilityViews.Brief.class, controller.getBriefViewClass());
        assertEquals(AbilityViews.Full.class, controller.getFullViewClass());
    }
}