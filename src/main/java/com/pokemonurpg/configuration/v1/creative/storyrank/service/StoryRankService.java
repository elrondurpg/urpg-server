package com.pokemonurpg.configuration.v1.creative.storyrank.service;

import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.configuration.v1.creative.storyrank.input.StoryRankInputDto;
import com.pokemonurpg.configuration.v1.creative.storyrank.model.StoryRank;
import com.pokemonurpg.configuration.v1.creative.storyrank.repository.StoryRankRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoryRankService extends SimpleNamedConfigurationService<StoryRank, StoryRankInputDto> {

    @Autowired
    public StoryRankService(StoryRankRepository repo) {
        super(repo, StoryRank.class);
    }

    @Override
    public void updateBase(StoryRank model, StoryRankInputDto input) {
        super.updateBase(model, input);
        setIfNotNull(input.getRequirement(), model::setRequirement);
    }
}
