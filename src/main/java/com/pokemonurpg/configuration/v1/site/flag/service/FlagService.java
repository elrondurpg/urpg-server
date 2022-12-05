package com.pokemonurpg.configuration.v1.site.flag.service;

import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.configuration.v1.site.flag.input.FlagInputDto;
import com.pokemonurpg.entities.v1.site.Flag;
import com.pokemonurpg.entities.v1.site.FlagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlagService extends NamedConfigurationService<Flag, FlagInputDto> {

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

    @Override
    protected void updateEmbeddedValues(Flag model, FlagInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void updateAssociatedValues(Flag model, FlagInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void deleteAssociatedValues(Flag model) {
        // TODO Auto-generated method stub
        
    }
}
