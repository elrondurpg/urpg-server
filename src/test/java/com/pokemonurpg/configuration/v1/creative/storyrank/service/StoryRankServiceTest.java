package com.pokemonurpg.configuration.v1.creative.storyrank.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.creative.storyrank.input.StoryRankInputTestDto;
import com.pokemonurpg.configuration.v1.creative.storyrank.model.StoryRank;
import com.pokemonurpg.configuration.v1.creative.storyrank.repository.StoryRankRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StoryRankServiceTest {

    @InjectMocks
    private StoryRankService service;

    @Mock
    private StoryRankRepository repository;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(StoryRank.class, service.getModelClass());
    }

    @Test
    public void test_updateBase() {
        StoryRankInputTestDto input = new StoryRankInputTestDto();
        StoryRank model = new StoryRank();
        service.updateBase(model, input);
        assertEquals(input.getName(), model.getName());
        assertEquals(input.getRequirement(), model.getRequirement());
    }

}