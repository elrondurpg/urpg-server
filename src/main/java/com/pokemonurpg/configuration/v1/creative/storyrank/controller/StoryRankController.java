package com.pokemonurpg.configuration.v1.creative.storyrank.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.creative.storyrank.StoryRankViews;
import com.pokemonurpg.configuration.v1.creative.storyrank.input.StoryRankInputDto;
import com.pokemonurpg.configuration.v1.creative.storyrank.model.StoryRank;
import com.pokemonurpg.configuration.v1.creative.storyrank.service.StoryRankService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/creative/storyrank")
@CrossOrigin
@Validated
public class StoryRankController extends NamedConfigurationController<StoryRank, FilterlessGetParams<StoryRank>, StoryRankInputDto> {

    @Autowired
    public StoryRankController(StoryRankService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(StoryRankViews.Id.class)
            .withBriefViewClass(StoryRankViews.Brief.class)
            .withFullViewClass(StoryRankViews.Brief.class)
            .build(), service);
    }
}
