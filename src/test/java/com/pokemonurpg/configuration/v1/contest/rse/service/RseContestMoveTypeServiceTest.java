package com.pokemonurpg.configuration.v1.contest.rse.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.contest.rse.input.RseContestMoveTypeInputTestDto;
import com.pokemonurpg.configuration.v1.contest.rse.model.RseContestMoveType;
import com.pokemonurpg.configuration.v1.contest.rse.repository.RseContestMoveTypeRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RseContestMoveTypeServiceTest {
    @InjectMocks
    private RseContestMoveTypeService service;

    @Mock
    private RseContestMoveTypeRepository repository;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(RseContestMoveType.class, service.getModelClass());
    }

    @Test
    public void test_updateBase() {
        RseContestMoveType model = new RseContestMoveType();
        RseContestMoveTypeInputTestDto input = new RseContestMoveTypeInputTestDto();
        service.updateBase(model, input);
        assertEquals(input.getName(), model.getName());
        assertEquals(input.getDescription(), model.getDescription());
        assertEquals(input.getScore(), model.getScore());
        assertEquals(input.getJam(), model.getJam());
    }
}