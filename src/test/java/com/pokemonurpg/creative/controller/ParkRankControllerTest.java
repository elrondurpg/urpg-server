package com.pokemonurpg.creative.controller;

import com.pokemonurpg.creative.models.ParkRank;
import com.pokemonurpg.creative.input.ParkRankInputDto;
import com.pokemonurpg.creative.service.ParkRankService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParkRankControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private ParkRankController parkRankController;

    @Mock
    private ParkRankService parkRankService;

    private ParkRank parkRank = new ParkRank();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(parkRankService.findAllNames()).thenReturn(names);
        assertEquals(names, parkRankController.findAllNames());
    }

    @Test
    public void findByName() {
        when(parkRankService.findByName(NAME)).thenReturn(parkRank);
        assertEquals(parkRank, parkRankController.findByName(NAME));
    }

    @Test
    public void create() {
        ParkRankInputDto input = new ParkRankInputDto();
        input.setName(NAME);
        when(parkRankService.create(input)).thenReturn(parkRank);
        assertEquals(parkRank, parkRankController.create(input));
    }

    @Test
    public void update() {
        ParkRankInputDto input = new ParkRankInputDto();
        input.setName(NAME);
        when(parkRankService.update(input, DBID)).thenReturn(parkRank);
        assertEquals(parkRank, parkRankController.update(input, DBID));
    }

}