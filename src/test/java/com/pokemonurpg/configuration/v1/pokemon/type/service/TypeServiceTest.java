package com.pokemonurpg.configuration.v1.pokemon.type.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.pokemon.type.input.TypeInputTestDto;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.configuration.v1.pokemon.type.repository.TypeRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TypeServiceTest {

    @InjectMocks
    private TypeService service;

    @Mock
    private TypeRepository typeRepository;

    @Test
    public void test_createBase() {
        TypeInputTestDto input = new TypeInputTestDto();
        Type type = service.createBase(input);
        assertNotNull(type);
    }
}