package com.pokemonurpg.configuration.v1.site.section.service;

import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.configuration.v1.site.section.input.SectionInputDto;
import com.pokemonurpg.entities.v1.site.Section;
import com.pokemonurpg.entities.v1.site.SectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService extends NamedConfigurationService<Section, SectionInputDto> {

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

    @Override
    protected void updateEmbeddedValues(Section model, SectionInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void updateAssociatedValues(Section model, SectionInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void deleteAssociatedValues(Section model) {
        // TODO Auto-generated method stub
        
    }
}
