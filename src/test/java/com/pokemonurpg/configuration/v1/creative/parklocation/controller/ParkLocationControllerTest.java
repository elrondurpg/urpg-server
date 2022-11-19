package com.pokemonurpg.configuration.v1.creative.parklocation.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.creative.parklocation.ParkLocationViews;
import com.pokemonurpg.configuration.v1.creative.parklocation.service.ParkLocationService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ParkLocationControllerTest {

    @InjectMocks
    private ParkLocationController controller;

    @Mock
    private ParkLocationService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(ParkLocationViews.Id.class, controller.getIdViewClass());
        assertEquals(ParkLocationViews.Brief.class, controller.getBriefViewClass());
        assertEquals(ParkLocationViews.Brief.class, controller.getFullViewClass());
    }

}