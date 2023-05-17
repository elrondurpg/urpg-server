package com.pokemonurpg.creative.controller;

import com.pokemonurpg.configuration.v1.parklocations.ParkLocationController;
import com.pokemonurpg.entities.ParkLocation;
import com.pokemonurpg.configuration.v1.parklocations.ParkLocationInputDto;
import com.pokemonurpg.configuration.v1.parklocations.ParkLocationService;
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
public class ParkLocationControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private ParkLocationController parkLocationController;

    @Mock
    private ParkLocationService parkLocationService;

    private ParkLocation parkLocation = new ParkLocation();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(parkLocationService.findAllNames()).thenReturn(names);
        assertEquals(names, parkLocationController.findAllNames());
    }

    @Test
    public void findByName() {
        when(parkLocationService.findByName(NAME)).thenReturn(parkLocation);
        assertEquals(parkLocation, parkLocationController.findByName(NAME));
    }

    @Test
    public void create() {
        ParkLocationInputDto input = new ParkLocationInputDto();
        input.setName(NAME);
        when(parkLocationService.create(input)).thenReturn(parkLocation);
        assertEquals(parkLocation, parkLocationController.create(input));
    }

    @Test
    public void update() {
        ParkLocationInputDto input = new ParkLocationInputDto();
        input.setName(NAME);
        when(parkLocationService.update(input, DBID)).thenReturn(parkLocation);
        assertEquals(parkLocation, parkLocationController.update(input, DBID));
    }

}