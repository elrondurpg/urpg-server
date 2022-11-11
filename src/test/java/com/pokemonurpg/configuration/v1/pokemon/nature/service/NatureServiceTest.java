package com.pokemonurpg.configuration.v1.pokemon.nature.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.pokemon.nature.input.NatureInputTestDto;
import com.pokemonurpg.configuration.v1.pokemon.nature.model.Nature;
import com.pokemonurpg.configuration.v1.pokemon.nature.repository.NatureRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class NatureServiceTest {

    @InjectMocks
    private NatureService service;

    @Mock
    private NatureRepository natureRepository;

    @Test
    public void test_createBase() {
        NatureInputTestDto input = new NatureInputTestDto();
        Nature nature = service.createBase(input);
        assertNotNull(nature);
    }

}