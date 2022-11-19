package com.pokemonurpg.configuration.v1.gym.gymownershipterm.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.gymownershipterm.GymOwnershipTermViews;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.service.GymOwnershipTermService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GymOwnershipTermControllerTest {

    @InjectMocks
    private GymOwnershipTermController controller;

    @Mock
    private GymOwnershipTermService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(GymOwnershipTermViews.Id.class, controller.getIdViewClass());
        assertEquals(GymOwnershipTermViews.Brief.class, controller.getBriefViewClass());
        assertEquals(GymOwnershipTermViews.Brief.class, controller.getFullViewClass());
    }

}