package com.pokemonurpg.configuration.v1.contest.rse.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.contest.ContestMoveTypeViews;
import com.pokemonurpg.configuration.v1.contest.rse.service.RseContestMoveTypeService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RseContestMoveTypeControllerTest {

    @InjectMocks
    private RseContestMoveTypeController controller;

    @Mock
    private RseContestMoveTypeService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(ContestMoveTypeViews.Id.class, controller.getIdViewClass());
        assertEquals(ContestMoveTypeViews.Brief.class, controller.getBriefViewClass());
        assertEquals(ContestMoveTypeViews.Brief.class, controller.getFullViewClass());
    }
}