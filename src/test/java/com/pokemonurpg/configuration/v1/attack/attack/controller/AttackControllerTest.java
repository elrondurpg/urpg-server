package com.pokemonurpg.configuration.v1.attack.attack.controller;

import com.pokemonurpg.configuration.v1.attack.attack.AttackViews;
import com.pokemonurpg.configuration.v1.attack.attack.service.AttackService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AttackControllerTest {

    @InjectMocks
    private AttackController controller;

    @Mock
    private AttackService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(AttackViews.Id.class, controller.getIdViewClass());
        assertEquals(AttackViews.Brief.class, controller.getBriefViewClass());
        assertEquals(AttackViews.Full.class, controller.getFullViewClass());
    }
}