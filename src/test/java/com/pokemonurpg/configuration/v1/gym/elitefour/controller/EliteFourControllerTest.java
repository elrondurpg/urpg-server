package com.pokemonurpg.configuration.v1.gym.elitefour.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.elitefour.EliteFourViews;
import com.pokemonurpg.configuration.v1.gym.elitefour.service.EliteFourService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EliteFourControllerTest {

    @InjectMocks
    private EliteFourController controller;

    @Mock
    private EliteFourService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(EliteFourViews.Id.class, controller.getIdViewClass());
        assertEquals(EliteFourViews.Brief.class, controller.getBriefViewClass());
        assertEquals(EliteFourViews.Full.class, controller.getFullViewClass());
    }

}