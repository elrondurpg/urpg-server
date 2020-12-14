package com.pokemonurpg.species.service;

import com.pokemonurpg.species.input.TypeInputDto;
import com.pokemonurpg.species.models.Type;
import com.pokemonurpg.species.repository.TypeRepository;
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
public class TypeServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private TypeService typeService;

    @Mock
    private TypeRepository typeRepository;

    private Type type = new Type();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(typeRepository.findAllNames()).thenReturn(types);

        assertEquals(types, typeService.findAllNames());
    }

    @Test
    public void findAllReturnsValueFromRepository() {
        List<Type> types = new ArrayList<>();
        when(typeRepository.findAll()).thenReturn(types);
        assertEquals(types, typeService.findAll());
    }

    @Test
    public void findByDbid() {
        when(typeRepository.findByDbid(DBID)).thenReturn(type);
        assertEquals(type, typeService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(typeRepository.findByName(NAME)).thenReturn(type);
        assertEquals(type, typeService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(typeRepository.findByName(NAME)).thenReturn(null);
        when(typeRepository.findFirstByNameStartingWith(NAME)).thenReturn(type);
        assertEquals(type, typeService.findByName(NAME));
    }

    @Test
    public void create() {
        TypeInputDto input = new TypeInputDto();
        input.setName(NAME);

        Type type = typeService.create(input);
        assertEquals(NAME, type.getName());
        verify(typeRepository, times(1)).save(type);
    }

    @Test
    public void updateExistingRecord() {
        TypeInputDto input = new TypeInputDto();
        input.setName(NAME);

        when(typeRepository.findByDbid(DBID)).thenReturn(type);

        Type type1 = typeService.update(input, DBID);
        assertEquals(type, type1);
        assertEquals(NAME, type1.getName());
        verify(typeRepository, times(1)).save(type1);
    }

    @Test
    public void updateNonExistingRecord() {
        TypeInputDto input = new TypeInputDto();
        input.setName(NAME);

        when(typeRepository.findByDbid(DBID)).thenReturn(null);

        Type type1 = typeService.update(input, DBID);
        assertNull(type1);
        verify(typeRepository, times(0)).save(Matchers.any());
    }

}