package com.pokemonurpg.configuration.v1.contest.rse.service;

import com.pokemonurpg.configuration.v1.contest.rse.input.RseContestMoveTypeInputDto;
import com.pokemonurpg.configuration.v1.contest.rse.model.RseContestMoveType;
import com.pokemonurpg.configuration.v1.lib.repository.NamedConfigurationRepository;
import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RseContestMoveTypeService extends SimpleNamedConfigurationService<RseContestMoveType, RseContestMoveTypeInputDto> {

    @Autowired
    public RseContestMoveTypeService(NamedConfigurationRepository<RseContestMoveType> repository) {
        super(repository, RseContestMoveType.class);
    }

    @Override
    public void updateBase(RseContestMoveType model, RseContestMoveTypeInputDto input) {
        super.updateBase(model, input);
        setIfNotNull(input.getDescription(), model::setDescription);
        setIfNotNull(input.getScore(), model::setScore);
        setIfNotNull(input.getJam(), model::setJam);
    }
}
