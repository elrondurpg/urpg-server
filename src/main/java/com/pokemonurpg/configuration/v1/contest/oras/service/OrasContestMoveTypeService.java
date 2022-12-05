package com.pokemonurpg.configuration.v1.contest.oras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.contest.oras.input.OrasContestMoveTypeInputDto;
import com.pokemonurpg.entities.v1.contest.OrasContestMoveType;
import com.pokemonurpg.entities.v1.contest.OrasContestMoveTypeRepository;
import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;

@Service
public class OrasContestMoveTypeService extends NamedConfigurationService<OrasContestMoveType, OrasContestMoveTypeInputDto> {

    @Autowired
    public OrasContestMoveTypeService(OrasContestMoveTypeRepository repository) {
        super(repository, OrasContestMoveType.class);
    }

    @Override
    public void updateBase(OrasContestMoveType model, OrasContestMoveTypeInputDto input) {
        super.updateBase(model, input);
        setIfNotNull(input.getDescription(), model::setDescription);
        setIfNotNull(input.getScore(), model::setScore);
        setIfNotNull(input.getJam(), model::setJam);
    }

    @Override
    protected void updateEmbeddedValues(OrasContestMoveType model, OrasContestMoveTypeInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void updateAssociatedValues(OrasContestMoveType model, OrasContestMoveTypeInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void deleteAssociatedValues(OrasContestMoveType model) {
        // TODO Auto-generated method stub
        
    }
}
