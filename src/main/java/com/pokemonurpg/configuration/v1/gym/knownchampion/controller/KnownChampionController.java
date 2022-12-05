package com.pokemonurpg.configuration.v1.gym.knownchampion.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.gym.knownchampion.KnownChampionViews;
import com.pokemonurpg.configuration.v1.gym.knownchampion.input.KnownChampionInputDto;
import com.pokemonurpg.entities.v1.gym.KnownChampion;
import com.pokemonurpg.configuration.v1.gym.knownchampion.service.KnownChampionService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/gym/knownchampion")
@CrossOrigin
@Validated
public class KnownChampionController extends NamedConfigurationController<KnownChampion, FilterlessGetParams<KnownChampion>, KnownChampionInputDto> {

    @Autowired
    public KnownChampionController(KnownChampionService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(KnownChampionViews.Id.class)
            .withBriefViewClass(KnownChampionViews.Id.class)
            .withFullViewClass(KnownChampionViews.Id.class)
            .build(), service);
    }
}
