package com.pokemonurpg.configuration.v1.pokemon.type.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.configuration.v1.pokemon.type.repository.TypeRepository;

@ExtendWith(MockitoExtension.class)
public class TypeServiceTest {

    @InjectMocks
    private TypeService service;

    @Mock
    private TypeRepository repository;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(Type.class, service.getModelClass());
    }
}