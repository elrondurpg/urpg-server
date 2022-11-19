package com.pokemonurpg.configuration.v1.creative.parkrank.service;

import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.configuration.v1.creative.parkrank.input.ParkRankInputDto;
import com.pokemonurpg.configuration.v1.creative.parkrank.model.ParkRank;
import com.pokemonurpg.configuration.v1.creative.parkrank.repository.ParkRankRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkRankService extends SimpleNamedConfigurationService<ParkRank, ParkRankInputDto> {

    @Autowired
    public ParkRankService(ParkRankRepository repo) {
        super(repo, ParkRank.class);
    }

    @Override
    public void updateBase(ParkRank model, ParkRankInputDto input) {
        super.updateBase(model, input);
        setIfNotNull(input.getRequirement(), model::setRequirement);
    }
}
