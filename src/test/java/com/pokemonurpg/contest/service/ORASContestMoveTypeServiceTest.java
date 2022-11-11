package com.pokemonurpg.contest.service;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;
import com.pokemonurpg.contest.models.ORASContestMoveType;
import com.pokemonurpg.contest.repository.ORASContestMoveTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ORASContestMoveTypeServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private ORASContestMoveTypeService orasContestMoveTypeService;

    @Mock
    private ORASContestMoveTypeRepository orasContestMoveTypeRepository;

    private ORASContestMoveType orasContestMoveType = new ORASContestMoveType();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(orasContestMoveTypeRepository.findAllNames()).thenReturn(types);

        assertEquals(types, orasContestMoveTypeService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(orasContestMoveTypeRepository.findByDbid(DBID)).thenReturn(orasContestMoveType);
        assertEquals(orasContestMoveType, orasContestMoveTypeService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(orasContestMoveTypeRepository.findByName(NAME)).thenReturn(orasContestMoveType);
        assertEquals(orasContestMoveType, orasContestMoveTypeService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(orasContestMoveTypeRepository.findByName(NAME)).thenReturn(null);
        when(orasContestMoveTypeRepository.findFirstByNameStartingWith(NAME)).thenReturn(orasContestMoveType);
        assertEquals(orasContestMoveType, orasContestMoveTypeService.findByName(NAME));
    }

    @Test
    public void create() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);

        ORASContestMoveType orasContestMoveType = orasContestMoveTypeService.create(input);
        assertEquals(NAME, orasContestMoveType.getName());
        verify(orasContestMoveTypeRepository, times(1)).save(orasContestMoveType);
    }

    @Test
    public void updateExistingRecord() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);

        when(orasContestMoveTypeRepository.findByDbid(DBID)).thenReturn(orasContestMoveType);

        ORASContestMoveType orasContestMoveType1 = orasContestMoveTypeService.update(input, DBID);
        assertEquals(orasContestMoveType, orasContestMoveType1);
        assertEquals(NAME, orasContestMoveType1.getName());
        verify(orasContestMoveTypeRepository, times(1)).save(orasContestMoveType1);
    }

    @Test
    public void updateNonExistingRecord() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);

        when(orasContestMoveTypeRepository.findByDbid(DBID)).thenReturn(null);

        ORASContestMoveType orasContestMoveType1 = orasContestMoveTypeService.update(input, DBID);
        assertNull(orasContestMoveType1);
        verify(orasContestMoveTypeRepository, times(0)).save(ArgumentMatchers.any());
    }
}