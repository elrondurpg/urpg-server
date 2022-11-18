package com.pokemonurpg.configuration.v1.contest.rse.controller;

import com.pokemonurpg.lib.input.v1.FilterlessGetParams;
import com.pokemonurpg.configuration.v1.contest.ContestMoveTypeViews;
import com.pokemonurpg.configuration.v1.contest.rse.input.RseContestMoveTypeInputDto;
import com.pokemonurpg.configuration.v1.contest.rse.model.RseContestMoveType;
import com.pokemonurpg.configuration.v1.contest.rse.service.RseContestMoveTypeService;
import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/contest/rseContestMoveType")
@CrossOrigin
@Validated
public class RseContestMoveTypeController extends NamedConfigurationController<RseContestMoveType, FilterlessGetParams<RseContestMoveType>, RseContestMoveTypeInputDto> {

    @Autowired
    public RseContestMoveTypeController(RseContestMoveTypeService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(ContestMoveTypeViews.Id.class)
            .withBriefViewClass(ContestMoveTypeViews.Brief.class)
            .withFullViewClass(ContestMoveTypeViews.Brief.class)
            .build(), service);
    }
}
