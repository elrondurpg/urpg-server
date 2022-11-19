package com.pokemonurpg.configuration.v1.site.section.service;

import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.configuration.v1.site.section.input.SectionInputDto;
import com.pokemonurpg.configuration.v1.site.section.model.Section;
import com.pokemonurpg.configuration.v1.site.section.repository.SectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService extends SimpleNamedConfigurationService<Section, SectionInputDto> {

    @Autowired
    public SectionService(SectionRepository repo) {
        super(repo, Section.class);
    }

    @Override
    public void updateBase(Section model, SectionInputDto input) {
        super.updateBase(model, input);
        setIfNotNull(input.getTier1LegendaryRequirement(), model::setTier1LegendaryRequirement);
        setIfNotNull(input.getTier2LegendaryRequirement(), model::setTier2LegendaryRequirement);
    }
}
