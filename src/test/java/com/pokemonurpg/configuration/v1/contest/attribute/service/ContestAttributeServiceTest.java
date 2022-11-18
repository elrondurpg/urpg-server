package com.pokemonurpg.configuration.v1.contest.attribute.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.contest.attribute.model.ContestAttribute;
import com.pokemonurpg.configuration.v1.contest.attribute.repository.ContestAttributeRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ContestAttributeServiceTest {

    @InjectMocks
    private ContestAttributeService service;

    @Mock
    private ContestAttributeRepository repository;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(ContestAttribute.class, service.getModelClass());
    }

}