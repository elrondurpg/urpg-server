package com.pokemonurpg.species.controller;

import com.pokemonurpg.configuration.v1.types.TypeController;
import com.pokemonurpg.entities.Type;
import com.pokemonurpg.configuration.v1.types.TypeInputDto;
import com.pokemonurpg.configuration.v1.types.TypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TypeControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private TypeController typeController;

    @Mock
    private TypeService typeService;

    private Type type = new Type();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(typeService.findAllNames()).thenReturn(names);
        assertEquals(names, typeController.findAllNames());
    }

    @Test
    public void findByName() {
        when(typeService.findByName(NAME)).thenReturn(type);
        assertEquals(type, typeController.findByName(NAME));
    }

    @Test
    public void create() {
        TypeInputDto input = new TypeInputDto();
        input.setName(NAME);
        when(typeService.create(input)).thenReturn(type);
        assertEquals(type, typeController.create(input));
    }

    @Test
    public void update() {
        TypeInputDto input = new TypeInputDto();
        input.setName(NAME);
        when(typeService.update(input, DBID)).thenReturn(type);
        assertEquals(type, typeController.update(input, DBID));
    }

}