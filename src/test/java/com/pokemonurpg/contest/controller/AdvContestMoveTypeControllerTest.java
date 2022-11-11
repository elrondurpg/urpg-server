package com.pokemonurpg.contest.controller;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;
import com.pokemonurpg.contest.models.AdvContestMoveType;
import com.pokemonurpg.contest.service.AdvContestMoveTypeService;
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
public class AdvContestMoveTypeControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private AdvContestMoveTypeController advContestMoveTypeController;

    @Mock
    private AdvContestMoveTypeService advContestMoveTypeService;

    private AdvContestMoveType advContestMoveType = new AdvContestMoveType();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(advContestMoveTypeService.findAllNames()).thenReturn(names);
        assertEquals(names, advContestMoveTypeController.findAllNames());
    }

    @Test
    public void findByName() {
        when(advContestMoveTypeService.findByName(NAME)).thenReturn(advContestMoveType);
        assertEquals(advContestMoveType, advContestMoveTypeController.findByName(NAME));
    }

    @Test
    public void create() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);
        when(advContestMoveTypeService.create(input)).thenReturn(advContestMoveType);
        assertEquals(advContestMoveType, advContestMoveTypeController.create(input));
    }

    @Test
    public void update() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);
        when(advContestMoveTypeService.update(input, DBID)).thenReturn(advContestMoveType);
        assertEquals(advContestMoveType, advContestMoveTypeController.update(input, DBID));
    }
}