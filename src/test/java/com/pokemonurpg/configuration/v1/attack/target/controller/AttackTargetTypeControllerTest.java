package com.pokemonurpg.configuration.v1.attack.target.controller;

import com.pokemonurpg.configuration.v1.attack.target.AttackTargetTypeViews;
import com.pokemonurpg.configuration.v1.attack.target.service.AttackTargetTypeService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AttackTargetTypeControllerTest {

    @InjectMocks
    private AttackTargetTypeController controller;

    @Mock
    private AttackTargetTypeService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(AttackTargetTypeViews.Id.class, controller.getIdViewClass());
        assertEquals(AttackTargetTypeViews.Brief.class, controller.getBriefViewClass());
        assertEquals(AttackTargetTypeViews.Brief.class, controller.getFullViewClass());
    }
}