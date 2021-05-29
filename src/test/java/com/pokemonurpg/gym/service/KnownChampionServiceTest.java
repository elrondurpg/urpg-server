package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.models.KnownChampion;
import com.pokemonurpg.gym.input.KnownChampionInputDto;
import com.pokemonurpg.gym.repository.KnownChampionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class KnownChampionServiceTest {
    private final static List<String> ALL_NAMES = new ArrayList<>();
    private final static String NAME = "NAME";
    private final static KnownChampion KNOWN_CHAMPION = mock(KnownChampion.class);

    @InjectMocks
    private KnownChampionService knownChampionService;

    @Mock
    private KnownChampionRepository knownChampionRepository;

    @Captor
    ArgumentCaptor<KnownChampion> captor;

    @Test
    public void findAllNames() {
        when(knownChampionRepository.findAllNames()).thenReturn(ALL_NAMES);
        assertEquals(ALL_NAMES, knownChampionService.findAllNames());
    }

    @Test
    public void findByName() {
        when(knownChampionRepository.findByName(NAME)).thenReturn(KNOWN_CHAMPION);
        assertEquals(KNOWN_CHAMPION, knownChampionService.findByName(NAME));
    }

    @Test
    public void findFirstByNameStartingWith() {
        when(knownChampionRepository.findFirstByNameStartingWith(NAME)).thenReturn(KNOWN_CHAMPION);
        assertEquals(KNOWN_CHAMPION, knownChampionService.findByName(NAME));
    }

    @Test
    public void create() {
        KnownChampionInputDto input = new KnownChampionInputDto();
        input.setName(NAME);

        when(knownChampionRepository.findByName(NAME)).thenReturn(null);
        knownChampionService.update(input);

        verify(knownChampionRepository, times(1)).save(captor.capture());
        KnownChampion savedObject = captor.getValue();
        assertEquals(NAME, savedObject.getName());
    }

    @Test
    public void updateDelete() {
        KnownChampionInputDto input = new KnownChampionInputDto();
        input.setName(NAME);
        input.setDelete(true);

        when(knownChampionRepository.findByName(NAME)).thenReturn(KNOWN_CHAMPION);

        knownChampionService.update(input);
        verify(knownChampionRepository, times(1)).delete(KNOWN_CHAMPION);
    }
}