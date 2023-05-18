package com.pokemonurpg.creative.service;

import com.pokemonurpg.configuration.v1.parklocations.ParkLocationInputDto;
import com.pokemonurpg.entities.v1.ParkLocation;
import com.pokemonurpg.infrastructure.v1.data.jpa.ParkLocationRepository;
import com.pokemonurpg.configuration.v1.parklocations.ParkLocationService;
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
public class ParkLocationServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private ParkLocationService parkLocationService;

    @Mock
    private ParkLocationRepository parkLocationRepository;

    private ParkLocation parkLocation = new ParkLocation();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(parkLocationRepository.findAllNames()).thenReturn(types);

        assertEquals(types, parkLocationService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(parkLocationRepository.findByDbid(DBID)).thenReturn(parkLocation);
        assertEquals(parkLocation, parkLocationService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(parkLocationRepository.findByName(NAME)).thenReturn(parkLocation);
        assertEquals(parkLocation, parkLocationService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(parkLocationRepository.findByName(NAME)).thenReturn(null);
        when(parkLocationRepository.findFirstByNameStartingWith(NAME)).thenReturn(parkLocation);
        assertEquals(parkLocation, parkLocationService.findByName(NAME));
    }

    @Test
    public void create() {
        ParkLocationInputDto input = new ParkLocationInputDto();
        input.setName(NAME);

        ParkLocation parkLocation = parkLocationService.create(input);
        assertEquals(NAME, parkLocation.getName());
        verify(parkLocationRepository, times(1)).save(parkLocation);
    }

    @Test
    public void updateExistingRecord() {
        ParkLocationInputDto input = new ParkLocationInputDto();
        input.setName(NAME);

        when(parkLocationRepository.findByDbid(DBID)).thenReturn(parkLocation);

        ParkLocation parkLocation1 = parkLocationService.update(input, DBID);
        assertEquals(parkLocation, parkLocation1);
        assertEquals(NAME, parkLocation1.getName());
        verify(parkLocationRepository, times(1)).save(parkLocation1);
    }

    @Test
    public void updateNonExistingRecord() {
        ParkLocationInputDto input = new ParkLocationInputDto();
        input.setName(NAME);

        when(parkLocationRepository.findByDbid(DBID)).thenReturn(null);

        ParkLocation parkLocation1 = parkLocationService.update(input, DBID);
        assertNull(parkLocation1);
        verify(parkLocationRepository, times(0)).save(Matchers.any());
    }

}