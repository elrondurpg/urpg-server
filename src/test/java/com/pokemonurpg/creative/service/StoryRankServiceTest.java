package com.pokemonurpg.creative.service;

import com.pokemonurpg.creative.input.StoryRankInputDto;
import com.pokemonurpg.creative.models.StoryRank;
import com.pokemonurpg.creative.repository.StoryRankRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StoryRankServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private StoryRankService storyRankService;

    @Mock
    private StoryRankRepository storyRankRepository;

    private StoryRank storyRank = new StoryRank();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(storyRankRepository.findAllNames()).thenReturn(types);

        assertEquals(types, storyRankService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(storyRankRepository.findByDbid(DBID)).thenReturn(storyRank);
        assertEquals(storyRank, storyRankService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(storyRankRepository.findByName(NAME)).thenReturn(storyRank);
        assertEquals(storyRank, storyRankService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(storyRankRepository.findByName(NAME)).thenReturn(null);
        when(storyRankRepository.findFirstByNameStartingWith(NAME)).thenReturn(storyRank);
        assertEquals(storyRank, storyRankService.findByName(NAME));
    }

    @Test
    public void create() {
        StoryRankInputDto input = new StoryRankInputDto();
        input.setName(NAME);

        StoryRank storyRank = storyRankService.create(input);
        assertEquals(NAME, storyRank.getName());
        verify(storyRankRepository, times(1)).save(storyRank);
    }

    @Test
    public void updateExistingRecord() {
        StoryRankInputDto input = new StoryRankInputDto();
        input.setName(NAME);

        when(storyRankRepository.findByDbid(DBID)).thenReturn(storyRank);

        StoryRank storyRank1 = storyRankService.update(input, DBID);
        assertEquals(storyRank, storyRank1);
        assertEquals(NAME, storyRank1.getName());
        verify(storyRankRepository, times(1)).save(storyRank1);
    }

    @Test
    public void updateNonExistingRecord() {
        StoryRankInputDto input = new StoryRankInputDto();
        input.setName(NAME);

        when(storyRankRepository.findByDbid(DBID)).thenReturn(null);

        StoryRank storyRank1 = storyRankService.update(input, DBID);
        assertNull(storyRank1);
        verify(storyRankRepository, times(0)).save(Matchers.any());
    }

}