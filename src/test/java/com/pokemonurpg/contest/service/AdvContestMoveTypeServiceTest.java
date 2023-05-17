package com.pokemonurpg.contest.service;

import com.pokemonurpg.configuration.v1.contestmovetypes.ContestMoveTypeInputDto;
import com.pokemonurpg.entities.AdvContestMoveType;
import com.pokemonurpg.infrastructure.data.AdvContestMoveTypeRepository;
import com.pokemonurpg.configuration.v1.contestmovetypes.AdvContestMoveTypeService;
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
public class AdvContestMoveTypeServiceTest {
    private final static AdvContestMoveType    ADV_CONTEST_MOVE_TYPE = mock(AdvContestMoveType.class);
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private AdvContestMoveTypeService advContestMoveTypeService;

    @Mock
    private AdvContestMoveTypeRepository advContestMoveTypeRepository;

    private AdvContestMoveType advContestMoveType = new AdvContestMoveType();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(advContestMoveTypeRepository.findAllNames()).thenReturn(types);

        assertEquals(types, advContestMoveTypeService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(advContestMoveTypeRepository.findByDbid(DBID)).thenReturn(advContestMoveType);
        assertEquals(advContestMoveType, advContestMoveTypeService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(advContestMoveTypeRepository.findByName(NAME)).thenReturn(advContestMoveType);
        assertEquals(advContestMoveType, advContestMoveTypeService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(advContestMoveTypeRepository.findByName(NAME)).thenReturn(null);
        when(advContestMoveTypeRepository.findFirstByNameStartingWith(NAME)).thenReturn(advContestMoveType);
        assertEquals(advContestMoveType, advContestMoveTypeService.findByName(NAME));
    }

    @Test
    public void findByNameExact() {
        when(advContestMoveTypeRepository.findByName(NAME)).thenReturn(ADV_CONTEST_MOVE_TYPE);
        assertEquals(ADV_CONTEST_MOVE_TYPE, advContestMoveTypeService.findByNameExact(NAME));
    }

    @Test
    public void create() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);

        AdvContestMoveType advContestMoveType = advContestMoveTypeService.create(input);
        assertEquals(NAME, advContestMoveType.getName());
        verify(advContestMoveTypeRepository, times(1)).save(advContestMoveType);
    }

    @Test
    public void updateExistingRecord() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);

        when(advContestMoveTypeRepository.findByDbid(DBID)).thenReturn(advContestMoveType);

        AdvContestMoveType advContestMoveType1 = advContestMoveTypeService.update(input, DBID);
        assertEquals(advContestMoveType, advContestMoveType1);
        assertEquals(NAME, advContestMoveType1.getName());
        verify(advContestMoveTypeRepository, times(1)).save(advContestMoveType1);
    }

    @Test
    public void updateNonExistingRecord() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);

        when(advContestMoveTypeRepository.findByDbid(DBID)).thenReturn(null);

        AdvContestMoveType advContestMoveType1 = advContestMoveTypeService.update(input, DBID);
        assertNull(advContestMoveType1);
        verify(advContestMoveTypeRepository, times(0)).save(Matchers.any());
    }
}