package com.pokemonurpg.general.service;

import com.pokemonurpg.general.input.ObtainedInputDto;
import com.pokemonurpg.general.models.Obtained;
import com.pokemonurpg.general.repository.ObtainedRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ObtainedServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private ObtainedService obtainedService;

    @Mock
    private ObtainedRepository obtainedRepository;

    private Obtained obtained = new Obtained();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(obtainedRepository.findAllNames()).thenReturn(types);

        assertEquals(types, obtainedService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(obtainedRepository.findByDbid(DBID)).thenReturn(obtained);
        assertEquals(obtained, obtainedService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(obtainedRepository.findByName(NAME)).thenReturn(obtained);
        assertEquals(obtained, obtainedService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(obtainedRepository.findByName(NAME)).thenReturn(null);
        when(obtainedRepository.findFirstByNameStartingWith(NAME)).thenReturn(obtained);
        assertEquals(obtained, obtainedService.findByName(NAME));
    }

    @Test
    public void create() {
        ObtainedInputDto input = new ObtainedInputDto();
        input.setName(NAME);

        Obtained obtained = obtainedService.create(input);
        assertEquals(NAME, obtained.getName());
        verify(obtainedRepository, times(1)).save(obtained);
    }

    @Test
    public void updateExistingRecord() {
        ObtainedInputDto input = new ObtainedInputDto();
        input.setName(NAME);

        when(obtainedRepository.findByDbid(DBID)).thenReturn(obtained);

        Obtained obtained1 = obtainedService.update(input, DBID);
        assertEquals(obtained, obtained1);
        assertEquals(NAME, obtained1.getName());
        verify(obtainedRepository, times(1)).save(obtained1);
    }

    @Test
    public void updateNonExistingRecord() {
        ObtainedInputDto input = new ObtainedInputDto();
        input.setName(NAME);

        when(obtainedRepository.findByDbid(DBID)).thenReturn(null);

        Obtained obtained1 = obtainedService.update(input, DBID);
        assertNull(obtained1);
        verify(obtainedRepository, times(0)).save(ArgumentMatchers.any());
    }

}