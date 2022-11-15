package com.pokemonurpg.configuration.v1.pokemon.capturemethod.service;

import com.pokemonurpg.configuration.v1.lib.repository.NamedConfigurationRepository;
import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.input.CaptureMethodInputDto;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.model.CaptureMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaptureMethodService extends SimpleNamedConfigurationService<CaptureMethod, CaptureMethodInputDto> {

    @Autowired
    public CaptureMethodService(NamedConfigurationRepository<CaptureMethod> repository) {
        super(repository, CaptureMethod.class);
    }
}
