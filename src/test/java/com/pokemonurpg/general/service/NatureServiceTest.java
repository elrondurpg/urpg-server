package com.pokemonurpg.general.service;

import com.pokemonurpg.configuration.v1.natures.NatureInputDto;
import com.pokemonurpg.entities.Nature;
import com.pokemonurpg.infrastructure.data.NatureRepository;
import com.pokemonurpg.configuration.v1.natures.NatureService;
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
public class NatureServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private NatureService natureService;

    @Mock
    private NatureRepository natureRepository;

    private Nature nature = new Nature();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(natureRepository.findAllNames()).thenReturn(types);

        assertEquals(types, natureService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(natureRepository.findByDbid(DBID)).thenReturn(nature);
        assertEquals(nature, natureService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(natureRepository.findByName(NAME)).thenReturn(nature);
        assertEquals(nature, natureService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(natureRepository.findByName(NAME)).thenReturn(null);
        when(natureRepository.findFirstByNameStartingWith(NAME)).thenReturn(nature);
        assertEquals(nature, natureService.findByName(NAME));
    }

    @Test
    public void create() {
        NatureInputDto input = new NatureInputDto();
        input.setName(NAME);

        Nature nature = natureService.create(input);
        assertEquals(NAME, nature.getName());
        verify(natureRepository, times(1)).save(nature);
    }

    @Test
    public void updateExistingRecord() {
        NatureInputDto input = new NatureInputDto();
        input.setName(NAME);

        when(natureRepository.findByDbid(DBID)).thenReturn(nature);

        Nature nature1 = natureService.update(input, DBID);
        assertEquals(nature, nature1);
        assertEquals(NAME, nature1.getName());
        verify(natureRepository, times(1)).save(nature1);
    }

    @Test
    public void updateNonExistingRecord() {
        NatureInputDto input = new NatureInputDto();
        input.setName(NAME);

        when(natureRepository.findByDbid(DBID)).thenReturn(null);

        Nature nature1 = natureService.update(input, DBID);
        assertNull(nature1);
        verify(natureRepository, times(0)).save(Matchers.any());
    }

}