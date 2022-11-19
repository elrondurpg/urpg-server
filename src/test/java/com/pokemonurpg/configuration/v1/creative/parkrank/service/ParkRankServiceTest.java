package com.pokemonurpg.configuration.v1.creative.parkrank.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.creative.parkrank.input.ParkRankInputTestDto;
import com.pokemonurpg.configuration.v1.creative.parkrank.model.ParkRank;
import com.pokemonurpg.configuration.v1.creative.parkrank.repository.ParkRankRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ParkRankServiceTest {

    @InjectMocks
    private ParkRankService service;

    @Mock
    private ParkRankRepository repository;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(ParkRank.class, service.getModelClass());
    }

    @Test
    public void test_updateBase() {
        ParkRankInputTestDto input = new ParkRankInputTestDto();
        ParkRank model = new ParkRank();
        service.updateBase(model, input);
        assertEquals(input.getName(), model.getName());
        assertEquals(input.getRequirement(), model.getRequirement());
    }

}