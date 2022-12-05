package com.pokemonurpg.configuration.v1.contest.rse.service;

import com.pokemonurpg.configuration.v1.contest.rse.input.RseContestMoveTypeInputDto;
import com.pokemonurpg.entities.v1.contest.RseContestMoveType;
import com.pokemonurpg.entities.v1.contest.RseContestMoveTypeRepository;
import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RseContestMoveTypeService extends NamedConfigurationService<RseContestMoveType, RseContestMoveTypeInputDto> {

    @Autowired
    public RseContestMoveTypeService(RseContestMoveTypeRepository repository) {
        super(repository, RseContestMoveType.class);
    }

    @Override
    public void updateBase(RseContestMoveType model, RseContestMoveTypeInputDto input) {
        super.updateBase(model, input);
        setIfNotNull(input.getDescription(), model::setDescription);
        setIfNotNull(input.getScore(), model::setScore);
        setIfNotNull(input.getJam(), model::setJam);
    }

    @Override
    protected void updateEmbeddedValues(RseContestMoveType model, RseContestMoveTypeInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void updateAssociatedValues(RseContestMoveType model, RseContestMoveTypeInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void deleteAssociatedValues(RseContestMoveType model) {
        // TODO Auto-generated method stub
        
    }
}
