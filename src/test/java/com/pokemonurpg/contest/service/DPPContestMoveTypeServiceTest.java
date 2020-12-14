package com.pokemonurpg.contest.service;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;
import com.pokemonurpg.contest.models.DPPContestMoveType;
import com.pokemonurpg.contest.repository.DPPContestMoveTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DPPContestMoveTypeServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private DPPContestMoveTypeService dppContestMoveTypeService;

    @Mock
    private DPPContestMoveTypeRepository dppContestMoveTypeRepository;

    private DPPContestMoveType dppContestMoveType = new DPPContestMoveType();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(dppContestMoveTypeRepository.findAllNames()).thenReturn(types);

        assertEquals(types, dppContestMoveTypeService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(dppContestMoveTypeRepository.findByDbid(DBID)).thenReturn(dppContestMoveType);
        assertEquals(dppContestMoveType, dppContestMoveTypeService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(dppContestMoveTypeRepository.findByName(NAME)).thenReturn(dppContestMoveType);
        assertEquals(dppContestMoveType, dppContestMoveTypeService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(dppContestMoveTypeRepository.findByName(NAME)).thenReturn(null);
        when(dppContestMoveTypeRepository.findFirstByNameStartingWith(NAME)).thenReturn(dppContestMoveType);
        assertEquals(dppContestMoveType, dppContestMoveTypeService.findByName(NAME));
    }

    @Test
    public void create() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);

        DPPContestMoveType dppContestMoveType = dppContestMoveTypeService.create(input);
        assertEquals(NAME, dppContestMoveType.getName());
        verify(dppContestMoveTypeRepository, times(1)).save(dppContestMoveType);
    }

    @Test
    public void updateExistingRecord() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);

        when(dppContestMoveTypeRepository.findByDbid(DBID)).thenReturn(dppContestMoveType);

        DPPContestMoveType dppContestMoveType1 = dppContestMoveTypeService.update(input, DBID);
        assertEquals(dppContestMoveType, dppContestMoveType1);
        assertEquals(NAME, dppContestMoveType1.getName());
        verify(dppContestMoveTypeRepository, times(1)).save(dppContestMoveType1);
    }

    @Test
    public void updateNonExistingRecord() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);

        when(dppContestMoveTypeRepository.findByDbid(DBID)).thenReturn(null);

        DPPContestMoveType dppContestMoveType1 = dppContestMoveTypeService.update(input, DBID);
        assertNull(dppContestMoveType1);
        verify(dppContestMoveTypeRepository, times(0)).save(Matchers.any());
    }
}