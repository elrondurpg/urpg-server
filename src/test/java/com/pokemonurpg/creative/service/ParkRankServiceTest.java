package com.pokemonurpg.creative.service;

import com.pokemonurpg.configuration.v1.parkranks.ParkRankInputDto;
import com.pokemonurpg.entities.ParkRank;
import com.pokemonurpg.infrastructure.data.ParkRankRepository;
import com.pokemonurpg.configuration.v1.parkranks.ParkRankService;
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
public class ParkRankServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private ParkRankService parkRankService;

    @Mock
    private ParkRankRepository parkRankRepository;

    private ParkRank parkRank = new ParkRank();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(parkRankRepository.findAllNames()).thenReturn(types);

        assertEquals(types, parkRankService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(parkRankRepository.findByDbid(DBID)).thenReturn(parkRank);
        assertEquals(parkRank, parkRankService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(parkRankRepository.findByName(NAME)).thenReturn(parkRank);
        assertEquals(parkRank, parkRankService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(parkRankRepository.findByName(NAME)).thenReturn(null);
        when(parkRankRepository.findFirstByNameStartingWith(NAME)).thenReturn(parkRank);
        assertEquals(parkRank, parkRankService.findByName(NAME));
    }

    @Test
    public void create() {
        ParkRankInputDto input = new ParkRankInputDto();
        input.setName(NAME);

        ParkRank parkRank = parkRankService.create(input);
        assertEquals(NAME, parkRank.getName());
        verify(parkRankRepository, times(1)).save(parkRank);
    }

    @Test
    public void updateExistingRecord() {
        ParkRankInputDto input = new ParkRankInputDto();
        input.setName(NAME);

        when(parkRankRepository.findByDbid(DBID)).thenReturn(parkRank);

        ParkRank parkRank1 = parkRankService.update(input, DBID);
        assertEquals(parkRank, parkRank1);
        assertEquals(NAME, parkRank1.getName());
        verify(parkRankRepository, times(1)).save(parkRank1);
    }

    @Test
    public void updateNonExistingRecord() {
        ParkRankInputDto input = new ParkRankInputDto();
        input.setName(NAME);

        when(parkRankRepository.findByDbid(DBID)).thenReturn(null);

        ParkRank parkRank1 = parkRankService.update(input, DBID);
        assertNull(parkRank1);
        verify(parkRankRepository, times(0)).save(Matchers.any());
    }

}