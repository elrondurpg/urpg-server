package com.pokemonurpg.general.controller;

import com.pokemonurpg.general.models.Section;
import com.pokemonurpg.general.input.SectionInputDto;
import com.pokemonurpg.general.service.SectionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SectionControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private SectionController sectionController;

    @Mock
    private SectionService sectionService;

    private Section section = new Section();

    @Test
    public void findAll() {
        List<Section> sections = new ArrayList<>();
        when(sectionService.findAll()).thenReturn(sections);
        assertEquals(sections, sectionController.findAll());
    }

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(sectionService.findAllNames()).thenReturn(names);
        assertEquals(names, sectionController.findAllNames());
    }

    @Test
    public void findByName() {
        when(sectionService.findByName(NAME)).thenReturn(section);
        assertEquals(section, sectionController.findByName(NAME));
    }

    @Test
    public void create() {
        SectionInputDto input = new SectionInputDto();
        input.setName(NAME);
        when(sectionService.create(input)).thenReturn(section);
        assertEquals(section, sectionController.create(input));
    }

    @Test
    public void update() {
        SectionInputDto input = new SectionInputDto();
        input.setName(NAME);
        when(sectionService.update(input, DBID)).thenReturn(section);
        assertEquals(section, sectionController.update(input, DBID));
    }

}