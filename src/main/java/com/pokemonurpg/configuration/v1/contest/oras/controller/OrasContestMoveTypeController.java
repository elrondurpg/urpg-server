package com.pokemonurpg.configuration.v1.contest.oras.controller;

import com.pokemonurpg.lib.input.v1.FilterlessGetParams;
import com.pokemonurpg.configuration.v1.contest.ContestMoveTypeViews;
import com.pokemonurpg.configuration.v1.contest.oras.input.OrasContestMoveTypeInputDto;
import com.pokemonurpg.configuration.v1.contest.oras.model.OrasContestMoveType;
import com.pokemonurpg.configuration.v1.contest.oras.service.OrasContestMoveTypeService;
import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/contest/orasContestMoveType")
@CrossOrigin
@Validated
public class OrasContestMoveTypeController extends NamedConfigurationController<OrasContestMoveType, FilterlessGetParams<OrasContestMoveType>, OrasContestMoveTypeInputDto> {

    @Autowired
    public OrasContestMoveTypeController(OrasContestMoveTypeService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(ContestMoveTypeViews.Id.class)
            .withBriefViewClass(ContestMoveTypeViews.Brief.class)
            .withFullViewClass(ContestMoveTypeViews.Brief.class)
            .build(), service);
    }
}
