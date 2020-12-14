package com.pokemonurpg.contest.controller;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;
import com.pokemonurpg.contest.models.DPPContestMoveType;
import com.pokemonurpg.contest.service.DPPContestMoveTypeService;
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
public class DPPContestMoveTypeControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private DPPContestMoveTypeController dppContestMoveTypeController;

    @Mock
    private DPPContestMoveTypeService dppContestMoveTypeService;

    private DPPContestMoveType dppContestMoveType = new DPPContestMoveType();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(dppContestMoveTypeService.findAllNames()).thenReturn(names);
        assertEquals(names, dppContestMoveTypeController.findAllNames());
    }

    @Test
    public void findByName() {
        when(dppContestMoveTypeService.findByName(NAME)).thenReturn(dppContestMoveType);
        assertEquals(dppContestMoveType, dppContestMoveTypeController.findByName(NAME));
    }

    @Test
    public void create() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);
        when(dppContestMoveTypeService.create(input)).thenReturn(dppContestMoveType);
        assertEquals(dppContestMoveType, dppContestMoveTypeController.create(input));
    }

    @Test
    public void update() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);
        when(dppContestMoveTypeService.update(input, DBID)).thenReturn(dppContestMoveType);
        assertEquals(dppContestMoveType, dppContestMoveTypeController.update(input, DBID));
    }
}