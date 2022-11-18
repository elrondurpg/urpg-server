package com.pokemonurpg.configuration.v1.contest.rank.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.contest.rank.ContestRankViews;
import com.pokemonurpg.configuration.v1.contest.rank.service.ContestRankService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ContestRankControllerTest {

    @InjectMocks
    private ContestRankController controller;

    @Mock
    private ContestRankService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(ContestRankViews.Id.class, controller.getIdViewClass());
        assertEquals(ContestRankViews.Id.class, controller.getBriefViewClass());
        assertEquals(ContestRankViews.Id.class, controller.getFullViewClass());
    }

}