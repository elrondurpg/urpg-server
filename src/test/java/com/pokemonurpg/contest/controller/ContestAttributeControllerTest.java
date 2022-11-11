package com.pokemonurpg.contest.controller;

import com.pokemonurpg.contest.models.ContestAttribute;
import com.pokemonurpg.contest.input.ContestAttributeInputDto;
import com.pokemonurpg.contest.service.ContestAttributeService;
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
        ContestAttributeInputDto input = new ContestAttributeInputDto();
        input.setName(NAME);
        when(contestAttributeService.create(input)).thenReturn(contestAttribute);
        assertEquals(contestAttribute, contestAttributeController.create(input));
    }

    @Test
    public void update() {
        ContestAttributeInputDto input = new ContestAttributeInputDto();
        input.setName(NAME);
        when(contestAttributeService.update(input, DBID)).thenReturn(contestAttribute);
        assertEquals(contestAttribute, contestAttributeController.update(input, DBID));
    }

}