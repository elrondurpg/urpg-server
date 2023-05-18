package com.pokemonurpg.configuration.v1.contestranks;

import com.pokemonurpg.configuration.v1.contestmovetypes.ContestMoveTypeInputDto;
import com.pokemonurpg.entities.v1.RSEContestMoveType;
import com.pokemonurpg.infrastructure.v1.data.jpa.RSEContestMoveTypeRepository;
import com.pokemonurpg.configuration.v1.contestmovetypes.RSEContestMoveTypeService;
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
    }
}