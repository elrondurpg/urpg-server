package com.pokemonurpg.gym.controller;

import com.pokemonurpg.gym.input.KnownChampionBulkInputDto;
import com.pokemonurpg.gym.service.KnownChampionBulkService;
import com.pokemonurpg.gym.service.KnownChampionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class KnownChampionBulkControllerTest {
    private final static List<String> ALL_NAMES = new ArrayList<>();
    private final static KnownChampionBulkInputDto INPUT = Mockito.mock(KnownChampionBulkInputDto.class);

    @InjectMocks
    private KnownChampionBulkController knownChampionBulkController;

    @Mock
    private KnownChampionService knownChampionService;

    @Mock
    private KnownChampionBulkService knownChampionBulkService;

    @Test
    public void findAllNames() {
        Mockito.when(knownChampionService.findAllNames()).thenReturn(ALL_NAMES);
        assertEquals(ALL_NAMES, knownChampionBulkController.findAllNames());
    }

    @Test
    public void create() {
        Mockito.when(knownChampionBulkService.update(INPUT)).thenReturn(ALL_NAMES);
        assertEquals(ALL_NAMES, knownChampionBulkController.update(INPUT));
    }

}