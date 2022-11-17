package com.pokemonurpg.configuration.v1.contest.type.service;

import com.pokemonurpg.configuration.v1.contest.type.input.ContestTypeInputDto;
import com.pokemonurpg.configuration.v1.contest.type.model.ContestType;
import com.pokemonurpg.configuration.v1.lib.repository.NamedConfigurationRepository;
import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContestTypeService extends SimpleNamedConfigurationService<ContestType, ContestTypeInputDto> {

    @Autowired
    public ContestTypeService(NamedConfigurationRepository<ContestType> repository) {
        super(repository, ContestType.class);
    }
}
