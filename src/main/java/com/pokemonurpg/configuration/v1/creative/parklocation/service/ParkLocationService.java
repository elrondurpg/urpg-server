package com.pokemonurpg.configuration.v1.creative.parklocation.service;

import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.configuration.v1.creative.parklocation.input.ParkLocationInputDto;
import com.pokemonurpg.configuration.v1.creative.parklocation.model.ParkLocation;
import com.pokemonurpg.configuration.v1.creative.parklocation.repository.ParkLocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkLocationService extends SimpleNamedConfigurationService<ParkLocation, ParkLocationInputDto> {

    @Autowired
    public ParkLocationService(ParkLocationRepository repo) {
        super(repo, ParkLocation.class);
    }
}
