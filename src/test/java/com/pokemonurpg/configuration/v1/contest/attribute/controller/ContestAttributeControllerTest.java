package com.pokemonurpg.configuration.v1.contest.attribute.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.contest.attribute.ContestAttributeViews;
import com.pokemonurpg.configuration.v1.contest.attribute.service.ContestAttributeService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ContestAttributeControllerTest {

    @InjectMocks
    private ContestAttributeController controller;

    @Mock
    private ContestAttributeService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(ContestAttributeViews.Id.class, controller.getIdViewClass());
        assertEquals(ContestAttributeViews.Id.class, controller.getBriefViewClass());
        assertEquals(ContestAttributeViews.Id.class, controller.getFullViewClass());
    }

}