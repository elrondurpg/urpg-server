package com.pokemonurpg.configuration.v1.contest.type.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.contest.type.model.ContestType;
import com.pokemonurpg.configuration.v1.contest.type.repository.ContestTypeRepository;

@ExtendWith(MockitoExtension.class)
public class ContestTypeServiceTest {

    @InjectMocks
    private ContestTypeService service;

    @Mock
    private ContestTypeRepository repository;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(ContestType.class, service.getModelClass());
    }
}