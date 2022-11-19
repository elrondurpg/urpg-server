package com.pokemonurpg.configuration.v1.site.section.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.site.section.input.SectionInputTestDto;
import com.pokemonurpg.configuration.v1.site.section.model.Section;
import com.pokemonurpg.configuration.v1.site.section.repository.SectionRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SectionServiceTest {

    @InjectMocks
    private SectionService service;

    @Mock
    private SectionRepository repository;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(Section.class, service.getModelClass());
    }

    @Test
    public void test_updateBase() {
        SectionInputTestDto input = new SectionInputTestDto();
        Section model = new Section();
        service.updateBase(model, input);
        assertEquals(input.getName(), model.getName());
        assertEquals(input.getTier1LegendaryRequirement(), model.getTier1LegendaryRequirement());
        assertEquals(input.getTier2LegendaryRequirement(), model.getTier2LegendaryRequirement());
    }

}