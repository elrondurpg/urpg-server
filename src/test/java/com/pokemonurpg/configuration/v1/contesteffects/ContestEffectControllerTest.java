package com.pokemonurpg.configuration.v1.contesteffects;

import com.pokemonurpg.entities.v1.ORASContestEffect;
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
public class ContestEffectControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private ContestEffectController contestEffectController;

    @Mock
    private ContestEffectService contestEffectService;

    private ORASContestEffect orasContestMoveType = new ORASContestEffect();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(contestEffectService.findAllNames()).thenReturn(names);
        assertEquals(names, contestEffectController.findAllNames());
    }

    @Test
    public void findByName() {
        when(contestEffectService.findByName(NAME)).thenReturn(orasContestMoveType);
        assertEquals(orasContestMoveType, contestEffectController.findByName(NAME));
    }

    @Test
    public void create() {
        ContestEffectRequest input = new ContestEffectRequest();
        input.setName(NAME);
        when(contestEffectService.create(input)).thenReturn(orasContestMoveType);
        assertEquals(orasContestMoveType, contestEffectController.create(input));
    }

    @Test
    public void update() {
        ContestEffectRequest input = new ContestEffectRequest();
        input.setName(NAME);
        when(contestEffectService.update(input, DBID)).thenReturn(orasContestMoveType);
        assertEquals(orasContestMoveType, contestEffectController.update(input, DBID));
    }
}