package com.pokemonurpg.configuration.v1.contestattributes;

import com.pokemonurpg.entities.v1.ContestAttribute;
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
public class ContestAttributeControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private ContestAttributeController contestAttributeController;

    @Mock
    private ContestAttributeService contestAttributeService;

    private ContestAttribute contestAttribute = new ContestAttribute();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(contestAttributeService.findAllNames()).thenReturn(names);
        assertEquals(names, contestAttributeController.findAllNames());
    }

    @Test
    public void findByName() {
        when(contestAttributeService.findByName(NAME)).thenReturn(contestAttribute);
        assertEquals(contestAttribute, contestAttributeController.findByName(NAME));
    }

    @Test
    public void create() {
        ContestAttributeRequest input = new ContestAttributeRequest();
        input.setName(NAME);
        when(contestAttributeService.create(input)).thenReturn(contestAttribute);
        assertEquals(contestAttribute, contestAttributeController.create(input));
    }

    @Test
    public void update() {
        ContestAttributeRequest input = new ContestAttributeRequest();
        input.setName(NAME);
        when(contestAttributeService.update(input, DBID)).thenReturn(contestAttribute);
        assertEquals(contestAttribute, contestAttributeController.update(input, DBID));
    }

}