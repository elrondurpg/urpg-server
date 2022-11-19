package com.pokemonurpg.configuration.v1.pokemon.capturemethod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.input.CaptureMethodInputDto;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.model.CaptureMethod;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.repository.CaptureMethodRepository;

@Service
public class CaptureMethodService extends SimpleNamedConfigurationService<CaptureMethod, CaptureMethodInputDto> {

    @Autowired
    public CaptureMethodService(CaptureMethodRepository repository) {
        super(repository, CaptureMethod.class);
    }
}
