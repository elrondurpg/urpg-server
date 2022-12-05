package com.pokemonurpg.configuration.v1.contest.rank.controller;
import com.pokemonurpg.configuration.v1.contest.rank.ContestRankViews;
import com.pokemonurpg.configuration.v1.contest.rank.input.ContestRankInputDto;
import com.pokemonurpg.entities.v1.contest.ContestRank;
import com.pokemonurpg.configuration.v1.contest.rank.service.ContestRankService;
import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/contest/contestRank")
@CrossOrigin
@Validated
public class ContestRankController extends NamedConfigurationController<ContestRank, FilterlessGetParams<ContestRank>, ContestRankInputDto> {

    @Autowired
    public ContestRankController(ContestRankService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(ContestRankViews.Id.class)
            .withBriefViewClass(ContestRankViews.Id.class)
            .withFullViewClass(ContestRankViews.Id.class)
            .build(), service);
    }
}
