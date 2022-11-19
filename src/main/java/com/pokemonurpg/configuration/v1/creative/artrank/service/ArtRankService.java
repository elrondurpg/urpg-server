package com.pokemonurpg.configuration.v1.creative.artrank.service;

import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.configuration.v1.creative.artrank.input.ArtRankInputDto;
import com.pokemonurpg.configuration.v1.creative.artrank.model.ArtRank;
import com.pokemonurpg.configuration.v1.creative.artrank.repository.ArtRankRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtRankService extends SimpleNamedConfigurationService<ArtRank, ArtRankInputDto> {

    @Autowired
    public ArtRankService(ArtRankRepository repo) {
        super(repo, ArtRank.class);
    }

    @Override
    public void updateBase(ArtRank model, ArtRankInputDto input) {
        super.updateBase(model, input);
        setIfNotNull(input.getRequirement(), model::setRequirement);
    }
}
