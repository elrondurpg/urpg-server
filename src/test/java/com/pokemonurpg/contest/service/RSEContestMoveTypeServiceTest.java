package com.pokemonurpg.contest.service;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;
import com.pokemonurpg.contest.models.RSEContestMoveType;
import com.pokemonurpg.contest.repository.RSEContestMoveTypeRepository;
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
public class RSEContestMoveTypeServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private RSEContestMoveTypeService rseContestMoveTypeService;

    @Mock
    private RSEContestMoveTypeRepository rseContestMoveTypeRepository;

    private RSEContestMoveType rseContestMoveType = new RSEContestMoveType();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(rseContestMoveTypeRepository.findAllNames()).thenReturn(types);

        assertEquals(types, rseContestMoveTypeService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(rseContestMoveTypeRepository.findByDbid(DBID)).thenReturn(rseContestMoveType);
        assertEquals(rseContestMoveType, rseContestMoveTypeService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(rseContestMoveTypeRepository.findByName(NAME)).thenReturn(rseContestMoveType);
        assertEquals(rseContestMoveType, rseContestMoveTypeService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(rseContestMoveTypeRepository.findByName(NAME)).thenReturn(null);
        when(rseContestMoveTypeRepository.findFirstByNameStartingWith(NAME)).thenReturn(rseContestMoveType);
        assertEquals(rseContestMoveType, rseContestMoveTypeService.findByName(NAME));
    }

    @Test
    public void create() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);

        RSEContestMoveType rseContestMoveType = rseContestMoveTypeService.create(input);
        assertEquals(NAME, rseContestMoveType.getName());
        verify(rseContestMoveTypeRepository, times(1)).save(rseContestMoveType);
    }

    @Test
    public void updateExistingRecord() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);

        when(rseContestMoveTypeRepository.findByDbid(DBID)).thenReturn(rseContestMoveType);

        RSEContestMoveType rseContestMoveType1 = rseContestMoveTypeService.update(input, DBID);
        assertEquals(rseContestMoveType, rseContestMoveType1);
        assertEquals(NAME, rseContestMoveType1.getName());
        verify(rseContestMoveTypeRepository, times(1)).save(rseContestMoveType1);
    }

    @Test
    public void updateNonExistingRecord() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setName(NAME);

        when(rseContestMoveTypeRepository.findByDbid(DBID)).thenReturn(null);

        RSEContestMoveType rseContestMoveType1 = rseContestMoveTypeService.update(input, DBID);
        assertNull(rseContestMoveType1);
        verify(rseContestMoveTypeRepository, times(0)).save(ArgumentMatchers.any());
    }
}