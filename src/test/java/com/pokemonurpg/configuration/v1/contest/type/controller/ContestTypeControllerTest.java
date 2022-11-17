package com.pokemonurpg.configuration.v1.contest.type.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.contest.type.ContestTypeViews;
import com.pokemonurpg.configuration.v1.contest.type.service.ContestTypeService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ContestTypeControllerTest {

    @InjectMocks
    private ContestTypeController controller;

    @Mock
    private ContestTypeService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(ContestTypeViews.Id.class, controller.getIdViewClass());
        assertEquals(ContestTypeViews.Id.class, controller.getBriefViewClass());
        assertEquals(ContestTypeViews.Id.class, controller.getFullViewClass());
    }

}