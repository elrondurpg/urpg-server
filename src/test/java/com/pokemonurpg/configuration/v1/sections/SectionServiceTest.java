package com.pokemonurpg.configuration.v1.sections;

import com.pokemonurpg.configuration.v1.sections.SectionInputDto;
import com.pokemonurpg.entities.v1.Section;
import com.pokemonurpg.infrastructure.v1.data.jpa.SectionRepository;
import com.pokemonurpg.configuration.v1.sections.SectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SectionServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private SectionService sectionService;

    @Mock
    private SectionRepository sectionRepository;

    private Section section = new Section();

    @Test
    public void findAll() {
        List<Section> sections = new ArrayList<>();
        when(sectionRepository.findAll()).thenReturn(sections);
        assertEquals(sections, sectionService.findAll());
    }

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(sectionRepository.findAllNames()).thenReturn(types);

        assertEquals(types, sectionService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(sectionRepository.findByDbid(DBID)).thenReturn(section);
        assertEquals(section, sectionService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(sectionRepository.findByName(NAME)).thenReturn(section);
        assertEquals(section, sectionService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(sectionRepository.findByName(NAME)).thenReturn(null);
        when(sectionRepository.findFirstByNameStartingWith(NAME)).thenReturn(section);
        assertEquals(section, sectionService.findByName(NAME));
    }

    @Test
    public void create() {
        SectionInputDto input = new SectionInputDto();
        input.setName(NAME);

        Section section = sectionService.create(input);
        assertEquals(NAME, section.getName());
        verify(sectionRepository, times(1)).save(section);
    }

    @Test
    public void updateExistingRecord() {
        SectionInputDto input = new SectionInputDto();
        input.setName(NAME);

        when(sectionRepository.findByDbid(DBID)).thenReturn(section);

        Section section1 = sectionService.update(input, DBID);
        assertEquals(section, section1);
        assertEquals(NAME, section1.getName());
        verify(sectionRepository, times(1)).save(section1);
    }

    @Test
    public void updateNonExistingRecord() {
        SectionInputDto input = new SectionInputDto();
        input.setName(NAME);

        when(sectionRepository.findByDbid(DBID)).thenReturn(null);

        Section section1 = sectionService.update(input, DBID);
        assertNull(section1);
    }

}