package com.pokemonurpg.configuration.v1.natures;

import com.pokemonurpg.entities.v1.Nature;
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
public class NatureControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private NatureController natureController;

    @Mock
    private NatureService natureService;

    private Nature nature = new Nature();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(natureService.findAllNames()).thenReturn(names);
        assertEquals(names, natureController.findAllNames());
    }

    @Test
    public void findByName() {
        when(natureService.findByName(NAME)).thenReturn(nature);
        assertEquals(nature, natureController.findByName(NAME));
    }

    @Test
    public void create() {
        NatureRequest input = new NatureRequest();
        input.setName(NAME);
        when(natureService.create(input)).thenReturn(nature);
        assertEquals(nature, natureController.create(input));
    }

    @Test
    public void update() {
        NatureRequest input = new NatureRequest();
        input.setName(NAME);
        when(natureService.update(input, DBID)).thenReturn(nature);
        assertEquals(nature, natureController.update(input, DBID));
    }

}