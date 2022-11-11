package com.pokemonurpg.contest.controller;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;
import com.pokemonurpg.contest.models.ORASContestMoveType;
import com.pokemonurpg.contest.service.ORASContestMoveTypeService;
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
public class ORASContestMoveTypeControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private ORASContestMoveTypeController orasContestMoveTypeController;

    @Mock
    private ORASContestMoveTypeService orasContestMoveTypeService;

    private ORASContestMoveType orasContestMoveType = new ORASContestMoveType();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(orasContestMoveTypeService.findAllNames()).thenReturn(names);
        assertEquals(names, orasContestMoveTypeController.findAllNames());
    }

    @Test
    public void findByName() {
        when(orasContestMoveTypeService.findByName(NAME)).thenReturn(orasContestMoveType);
        assertEquals(orasContestMoveType, orasContestMoveTypeController.findByName(NAME));
    }

    @Test
    public void create() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);
        when(orasContestMoveTypeService.create(input)).thenReturn(orasContestMoveType);
        assertEquals(orasContestMoveType, orasContestMoveTypeController.create(input));
    }

    @Test
    public void update() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);
        when(orasContestMoveTypeService.update(input, DBID)).thenReturn(orasContestMoveType);
        assertEquals(orasContestMoveType, orasContestMoveTypeController.update(input, DBID));
    }
}