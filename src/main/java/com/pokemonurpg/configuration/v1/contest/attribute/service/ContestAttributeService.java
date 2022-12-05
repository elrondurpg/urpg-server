package com.pokemonurpg.configuration.v1.contest.attribute.service;

import com.pokemonurpg.configuration.v1.contest.attribute.input.ContestAttributeInputDto;
import com.pokemonurpg.entities.v1.contest.ContestAttribute;
import com.pokemonurpg.entities.v1.contest.ContestAttributeRepository;
import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContestAttributeService extends SimpleNamedConfigurationService<ContestAttribute, ContestAttributeInputDto> {

    @Autowired
    public ContestAttributeService(ContestAttributeRepository repository) {
        super(repository, ContestAttribute.class);
    }
}