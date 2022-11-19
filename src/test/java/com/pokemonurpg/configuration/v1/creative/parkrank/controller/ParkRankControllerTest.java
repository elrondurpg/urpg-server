package com.pokemonurpg.configuration.v1.creative.parkrank.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.creative.parkrank.ParkRankViews;
import com.pokemonurpg.configuration.v1.creative.parkrank.service.ParkRankService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ParkRankControllerTest {

    @InjectMocks
    private ParkRankController controller;

    @Mock
    private ParkRankService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(ParkRankViews.Id.class, controller.getIdViewClass());
        assertEquals(ParkRankViews.Brief.class, controller.getBriefViewClass());
        assertEquals(ParkRankViews.Brief.class, controller.getFullViewClass());
    }

}