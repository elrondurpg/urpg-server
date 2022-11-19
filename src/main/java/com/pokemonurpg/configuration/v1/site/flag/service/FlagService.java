package com.pokemonurpg.configuration.v1.site.flag.service;

import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.configuration.v1.site.flag.input.FlagInputDto;
import com.pokemonurpg.configuration.v1.site.flag.model.Flag;
import com.pokemonurpg.configuration.v1.site.flag.repository.FlagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlagService extends SimpleNamedConfigurationService<Flag, FlagInputDto> {

    @Autowired
    public FlagService(FlagRepository repo) {
        super(repo, Flag.class);
    }

    @Override
    public void updateBase(Flag model, FlagInputDto input) {
        super.updateBase(model, input);
        setIfNotNull(input.getDescription(), model::setDescription);
        setIfNotNull(input.getType(), model::setType);
        setIfNotNull(input.getValue(), model::setValue);
    }
}
