package com.pokemonurpg.configuration.v1.pokemon.type.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.pokemon.type.input.TypeInputDto;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.configuration.v1.pokemon.type.repository.TypeRepository;
import com.pokemonurpg.configuration.v1.pokemon.type.service.TypeService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
        verify(typeRepository, times(0)).save(ArgumentMatchers.any());
    }

}