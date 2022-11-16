package com.pokemonurpg.configuration.v1.pokemon.capturemethod.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.input.CaptureMethodInputTestDto;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.model.CaptureMethod;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.repository.CaptureMethodRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CaptureMethodServiceTest {

    @InjectMocks
    private CaptureMethodService service;

    @Mock
    private CaptureMethodRepository repository;

}