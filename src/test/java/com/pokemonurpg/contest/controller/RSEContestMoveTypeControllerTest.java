package com.pokemonurpg.contest.controller;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;
import com.pokemonurpg.contest.models.RSEContestMoveType;
import com.pokemonurpg.contest.service.RSEContestMoveTypeService;
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
public class RSEContestMoveTypeControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private RSEContestMoveTypeController rseContestMoveTypeController;

    @Mock
    private RSEContestMoveTypeService rseContestMoveTypeService;

    private RSEContestMoveType rseContestMoveType = new RSEContestMoveType();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(rseContestMoveTypeService.findAllNames()).thenReturn(names);
        assertEquals(names, rseContestMoveTypeController.findAllNames());
    }

    @Test
    public void findByName() {
        when(rseContestMoveTypeService.findByName(NAME)).thenReturn(rseContestMoveType);
        assertEquals(rseContestMoveType, rseContestMoveTypeController.findByName(NAME));
    }

    @Test
    public void create() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);
        when(rseContestMoveTypeService.create(input)).thenReturn(rseContestMoveType);
        assertEquals(rseContestMoveType, rseContestMoveTypeController.create(input));
    }

    @Test
    public void update() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);
        when(rseContestMoveTypeService.update(input, DBID)).thenReturn(rseContestMoveType);
        assertEquals(rseContestMoveType, rseContestMoveTypeController.update(input, DBID));
    }
}