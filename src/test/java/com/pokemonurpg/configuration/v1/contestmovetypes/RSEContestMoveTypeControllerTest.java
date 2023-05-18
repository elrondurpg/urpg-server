package com.pokemonurpg.configuration.v1.contestmovetypes;

import com.pokemonurpg.configuration.v1.contestmovetypes.RSEContestMoveTypeController;
import com.pokemonurpg.configuration.v1.contestmovetypes.ContestMoveTypeInputDto;
import com.pokemonurpg.entities.v1.RSEContestMoveType;
import com.pokemonurpg.configuration.v1.contestmovetypes.RSEContestMoveTypeService;
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