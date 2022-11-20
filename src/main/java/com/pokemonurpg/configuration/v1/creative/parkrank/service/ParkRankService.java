package com.pokemonurpg.configuration.v1.creative.parkrank.service;

import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.configuration.v1.creative.parkrank.input.ParkRankInputDto;
import com.pokemonurpg.configuration.v1.creative.parkrank.model.ParkRank;
import com.pokemonurpg.configuration.v1.creative.parkrank.repository.ParkRankRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkRankService extends NamedConfigurationService<ParkRank, ParkRankInputDto> {

    @Autowired
    public ParkRankService(ParkRankRepository repo) {
        super(repo, ParkRank.class);
    }

    @Override
    public void updateBase(ParkRank model, ParkRankInputDto input) {
        super.updateBase(model, input);
        setIfNotNull(input.getRequirement(), model::setRequirement);
    }

    @Override
    protected void updateEmbeddedValues(ParkRank model, ParkRankInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void updateAssociatedValues(ParkRank model, ParkRankInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void deleteAssociatedValues(ParkRank model) {
        // TODO Auto-generated method stub
        
    }
}
