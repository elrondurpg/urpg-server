package com.pokemonurpg.gym.controller;

import com.pokemonurpg.gym.input.KnownGymLeaderBulkInputDto;
import com.pokemonurpg.gym.service.KnownGymLeaderBulkService;
import com.pokemonurpg.gym.service.KnownGymLeaderService;
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
public class KnownGymLeaderBulkControllerTest {
    private final static List<String> ALL_NAMES = new ArrayList<>();
    private final static KnownGymLeaderBulkInputDto INPUT = Mockito.mock(KnownGymLeaderBulkInputDto.class);

    @InjectMocks
    private KnownGymLeaderBulkController knownGymLeaderBulkController;

    @Mock
    private KnownGymLeaderService knownGymLeaderService;

    @Mock
    private KnownGymLeaderBulkService knownGymLeaderBulkService;

    @Test
    public void findAllNames() {
        Mockito.when(knownGymLeaderService.findAllNames()).thenReturn(ALL_NAMES);
        assertEquals(ALL_NAMES, knownGymLeaderBulkController.findAllNames());
    }

    @Test
    public void create() {
        Mockito.when(knownGymLeaderBulkService.update(INPUT)).thenReturn(ALL_NAMES);
        assertEquals(ALL_NAMES, knownGymLeaderBulkController.update(INPUT));
    }

}