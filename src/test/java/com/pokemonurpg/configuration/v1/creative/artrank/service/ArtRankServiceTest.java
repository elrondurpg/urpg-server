package com.pokemonurpg.configuration.v1.creative.artrank.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.creative.artrank.input.ArtRankInputTestDto;
import com.pokemonurpg.configuration.v1.creative.artrank.model.ArtRank;
import com.pokemonurpg.configuration.v1.creative.artrank.repository.ArtRankRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ArtRankServiceTest {

    @InjectMocks
    private ArtRankService service;

    @Mock
    private ArtRankRepository repository;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(ArtRank.class, service.getModelClass());
    }

    @Test
    public void test_updateBase() {
        ArtRankInputTestDto input = new ArtRankInputTestDto();
        ArtRank model = new ArtRank();
        service.updateBase(model, input);
        assertEquals(input.getName(), model.getName());
        assertEquals(input.getRequirement(), model.getRequirement());
    }

}