package com.pokemonurpg.configuration.v1.contest.oras.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.contest.oras.input.OrasContestMoveTypeInputTestDto;
import com.pokemonurpg.configuration.v1.contest.oras.model.OrasContestMoveType;
import com.pokemonurpg.configuration.v1.contest.oras.repository.OrasContestMoveTypeRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrasContestMoveTypeServiceTest {
    @InjectMocks
    private OrasContestMoveTypeService service;

    @Mock
    private OrasContestMoveTypeRepository repository;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(OrasContestMoveType.class, service.getModelClass());
    }

    @Test
    public void test_updateBase() {
        OrasContestMoveType model = new OrasContestMoveType();
        OrasContestMoveTypeInputTestDto input = new OrasContestMoveTypeInputTestDto();
        service.updateBase(model, input);
        assertEquals(input.getName(), model.getName());
        assertEquals(input.getDescription(), model.getDescription());
        assertEquals(input.getScore(), model.getScore());
        assertEquals(input.getJam(), model.getJam());
    }
}