package com.pokemonurpg.configuration.v1.contest.type.service;

import com.pokemonurpg.configuration.v1.contest.type.input.ContestTypeInputDto;
import com.pokemonurpg.configuration.v1.contest.type.model.ContestType;
import com.pokemonurpg.configuration.v1.contest.type.repository.ContestTypeRepository;
import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContestTypeService extends SimpleNamedConfigurationService<ContestType, ContestTypeInputDto> {

    @Autowired
    public ContestTypeService(ContestTypeRepository repository) {
        super(repository, ContestType.class);
    }
}
