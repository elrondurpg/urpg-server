package com.pokemonurpg.creative.controller;

import com.pokemonurpg.creative.models.StoryRank;
import com.pokemonurpg.creative.input.StoryRankInputDto;
import com.pokemonurpg.creative.service.StoryRankService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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