package com.pokemonurpg.configuration.v1.contest.rank.service;

import com.pokemonurpg.configuration.v1.contest.rank.model.ContestRank;
import com.pokemonurpg.configuration.v1.contest.rank.repository.ContestRankRepository;
import com.pokemonurpg.configuration.v1.contest.rank.input.ContestRankInputDto;
import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContestRankService extends SimpleNamedConfigurationService<ContestRank, ContestRankInputDto> {

    @Autowired
    public ContestRankService(ContestRankRepository repository) {
        super(repository, ContestRank.class);
    }

    
}
