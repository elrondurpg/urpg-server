package com.pokemonurpg.configuration.v1.contest.type.service;

import com.pokemonurpg.configuration.v1.contest.type.input.ContestTypeInputDto;
import com.pokemonurpg.entities.v1.contest.ContestType;
import com.pokemonurpg.entities.v1.contest.ContestTypeRepository;
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
