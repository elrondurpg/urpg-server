package com.pokemonurpg.configuration.v1.contestranks;

import com.pokemonurpg.configuration.v1.contesteffects.ContestEffectRequest;
import com.pokemonurpg.entities.v1.ORASContestEffect;
import com.pokemonurpg.infrastructure.v1.data.jpa.ORASContestEffectRepository;
import com.pokemonurpg.configuration.v1.contesteffects.ContestEffectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ContestEffectServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private ContestEffectService contestEffectService;

    @Mock
    private ORASContestEffectRepository orasContestEffectRepository;

    private ORASContestEffect orasContestMoveType = new ORASContestEffect();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(orasContestEffectRepository.findAllNames()).thenReturn(types);

        assertEquals(types, contestEffectService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(orasContestEffectRepository.findByDbid(DBID)).thenReturn(orasContestMoveType);
        assertEquals(orasContestMoveType, contestEffectService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(orasContestEffectRepository.findByName(NAME)).thenReturn(orasContestMoveType);
        assertEquals(orasContestMoveType, contestEffectService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(orasContestEffectRepository.findByName(NAME)).thenReturn(null);
        when(orasContestEffectRepository.findFirstByNameStartingWith(NAME)).thenReturn(orasContestMoveType);
        assertEquals(orasContestMoveType, contestEffectService.findByName(NAME));
    }

    @Test
    public void create() {
        ContestEffectRequest input = new ContestEffectRequest();
        input.setName(NAME);

        ORASContestEffect orasContestMoveType = contestEffectService.create(input);
        assertEquals(NAME, orasContestMoveType.getName());
        verify(orasContestEffectRepository, times(1)).save(orasContestMoveType);
    }

    @Test
    public void updateExistingRecord() {
        ContestEffectRequest input = new ContestEffectRequest();
        input.setName(NAME);

        when(orasContestEffectRepository.findByDbid(DBID)).thenReturn(orasContestMoveType);

        ORASContestEffect orasContestMoveType1 = contestEffectService.update(input, DBID);
        assertEquals(orasContestMoveType, orasContestMoveType1);
        assertEquals(NAME, orasContestMoveType1.getName());
        verify(orasContestEffectRepository, times(1)).save(orasContestMoveType1);
    }

    @Test
    public void updateNonExistingRecord() {
        ContestEffectRequest input = new ContestEffectRequest();
        input.setName(NAME);

        when(orasContestEffectRepository.findByDbid(DBID)).thenReturn(null);

        ORASContestEffect orasContestMoveType1 = contestEffectService.update(input, DBID);
        assertNull(orasContestMoveType1);
    }
}