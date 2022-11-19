package com.pokemonurpg.configuration.v1.creative.artrank.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.creative.artrank.ArtRankViews;
import com.pokemonurpg.configuration.v1.creative.artrank.service.ArtRankService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ArtRankControllerTest {

    @InjectMocks
    private ArtRankController controller;

    @Mock
    private ArtRankService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(ArtRankViews.Id.class, controller.getIdViewClass());
        assertEquals(ArtRankViews.Id.class, controller.getBriefViewClass());
        assertEquals(ArtRankViews.Id.class, controller.getFullViewClass());
    }

}