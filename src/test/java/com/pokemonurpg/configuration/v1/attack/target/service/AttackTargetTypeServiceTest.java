package com.pokemonurpg.configuration.v1.attack.target.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.attack.target.model.AttackTargetType;
import com.pokemonurpg.configuration.v1.attack.target.repository.AttackTargetTypeRepository;

@ExtendWith(MockitoExtension.class)
public class AttackTargetTypeServiceTest {

    @InjectMocks
    private AttackTargetTypeService service;

    @Mock
    private AttackTargetTypeRepository repository;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(AttackTargetType.class, service.getModelClass());
    }
}