package com.pokemonurpg.configuration.v1.site.flag.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.site.flag.input.FlagInputTestDto;
import com.pokemonurpg.configuration.v1.site.flag.model.Flag;
import com.pokemonurpg.configuration.v1.site.flag.repository.FlagRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FlagServiceTest {

    @InjectMocks
    private FlagService service;

    @Mock
    private FlagRepository repository;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(Flag.class, service.getModelClass());
    }

    @Test
    public void test_updateBase() {
        FlagInputTestDto input = new FlagInputTestDto();
        Flag model = new Flag();
        service.updateBase(model, input);
        assertEquals(input.getName(), model.getName());
        assertEquals(input.getDescription(), model.getDescription());
        assertEquals(input.getType(), model.getType());
        assertEquals(input.getValue(), model.getValue());
    }

}