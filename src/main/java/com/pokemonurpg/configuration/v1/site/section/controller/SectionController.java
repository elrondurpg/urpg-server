package com.pokemonurpg.configuration.v1.site.section.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.site.section.SectionViews;
import com.pokemonurpg.configuration.v1.site.section.input.SectionInputDto;
import com.pokemonurpg.configuration.v1.site.section.model.Section;
import com.pokemonurpg.configuration.v1.site.section.service.SectionService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/site/section")
@CrossOrigin
@Validated
public class SectionController extends NamedConfigurationController<Section, FilterlessGetParams<Section>, SectionInputDto> {

    @Autowired
    public SectionController(SectionService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(SectionViews.Id.class)
            .withBriefViewClass(SectionViews.Brief.class)
            .withFullViewClass(SectionViews.Brief.class)
            .build(), service);
    }
}
