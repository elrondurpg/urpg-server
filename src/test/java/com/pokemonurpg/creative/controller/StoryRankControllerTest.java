package com.pokemonurpg.creative.controller;

import com.pokemonurpg.configuration.v1.storyranks.StoryRankController;
import com.pokemonurpg.entities.StoryRank;
import com.pokemonurpg.configuration.v1.storyranks.StoryRankInputDto;
import com.pokemonurpg.configuration.v1.storyranks.StoryRankService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StoryRankControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private StoryRankController storyRankController;

    @Mock
    private StoryRankService storyRankService;

    private StoryRank storyRank = new StoryRank();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(storyRankService.findAllNames()).thenReturn(names);
        assertEquals(names, storyRankController.findAllNames());
    }

    @Test
    public void findByName() {
        when(storyRankService.findByName(NAME)).thenReturn(storyRank);
        assertEquals(storyRank, storyRankController.findByName(NAME));
    }

    @Test
    public void create() {
        StoryRankInputDto input = new StoryRankInputDto();
        input.setName(NAME);
        when(storyRankService.create(input)).thenReturn(storyRank);
        assertEquals(storyRank, storyRankController.create(input));
    }

    @Test
    public void update() {
        StoryRankInputDto input = new StoryRankInputDto();
        input.setName(NAME);
        when(storyRankService.update(input, DBID)).thenReturn(storyRank);
        assertEquals(storyRank, storyRankController.update(input, DBID));
    }

}