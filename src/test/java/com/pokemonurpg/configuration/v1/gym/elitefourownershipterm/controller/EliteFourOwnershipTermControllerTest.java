package com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.EliteFourOwnershipTermViews;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.service.EliteFourOwnershipTermService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EliteFourOwnershipTermControllerTest {

    @InjectMocks
    private EliteFourOwnershipTermController controller;

    @Mock
    private EliteFourOwnershipTermService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(EliteFourOwnershipTermViews.Id.class, controller.getIdViewClass());
        assertEquals(EliteFourOwnershipTermViews.Brief.class, controller.getBriefViewClass());
        assertEquals(EliteFourOwnershipTermViews.Brief.class, controller.getFullViewClass());
    }

}