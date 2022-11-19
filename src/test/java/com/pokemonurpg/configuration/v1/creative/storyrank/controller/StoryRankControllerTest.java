package com.pokemonurpg.configuration.v1.creative.storyrank.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.creative.storyrank.StoryRankViews;
import com.pokemonurpg.configuration.v1.creative.storyrank.service.StoryRankService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StoryRankControllerTest {

    @InjectMocks
    private StoryRankController controller;

    @Mock
    private StoryRankService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(StoryRankViews.Id.class, controller.getIdViewClass());
        assertEquals(StoryRankViews.Brief.class, controller.getBriefViewClass());
        assertEquals(StoryRankViews.Brief.class, controller.getFullViewClass());
    }

}