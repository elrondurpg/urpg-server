package com.pokemonurpg.configuration.v1.creative.storyrank.service;

import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.configuration.v1.creative.storyrank.input.StoryRankInputDto;
import com.pokemonurpg.entities.v1.creative.StoryRank;
import com.pokemonurpg.entities.v1.creative.StoryRankRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoryRankService extends NamedConfigurationService<StoryRank, StoryRankInputDto> {

    @Autowired
    public StoryRankService(StoryRankRepository repo) {
        super(repo, StoryRank.class);
    }

    @Override
    public void updateBase(StoryRank model, StoryRankInputDto input) {
        super.updateBase(model, input);
        setIfNotNull(input.getRequirement(), model::setRequirement);
    }

    @Override
    protected void updateEmbeddedValues(StoryRank model, StoryRankInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void updateAssociatedValues(StoryRank model, StoryRankInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void deleteAssociatedValues(StoryRank model) {
        // TODO Auto-generated method stub
        
    }
}
