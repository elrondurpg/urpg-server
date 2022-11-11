package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.models.KnownChampion;
import com.pokemonurpg.gym.input.KnownChampionInputDto;
import com.pokemonurpg.gym.repository.KnownChampionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KnownChampionServiceTest {
    private final static Integer DBID = 432;
    private final static List<String> ALL_NAMES = new ArrayList<>();
    private final static String NAME = "NAME";
    private final static String NEW_NAME = "NEW_NAME";
    private final static String OLD_NAME = "OLD_NAME";

    private KnownChampion knownChampion = new KnownChampion();

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
        when(knownChampionRepository.findByName(NAME)).thenReturn(knownChampion);
        assertEquals(knownChampion, knownChampionService.findByName(NAME));
    }

    @Test
    public void findFirstByNameStartingWith() {
        when(knownChampionRepository.findFirstByNameStartingWith(NAME)).thenReturn(knownChampion);
        assertEquals(knownChampion, knownChampionService.findByName(NAME));
    }

    @Test
    public void create() {
        when(knownChampionRepository.findByName(NAME)).thenReturn(null);
        knownChampionService.create(NAME);

        verify(knownChampionRepository, times(1)).save(captor.capture());
        KnownChampion savedObject = captor.getValue();
        assertEquals(NAME, savedObject.getName());
    }

    @Test
    public void createByInputDto() {
        KnownChampionInputDto input = new KnownChampionInputDto();
        input.setName(NAME);

        when(knownChampionRepository.findByName(NAME)).thenReturn(null);
        knownChampionService.update(input, DBID);

        verify(knownChampionRepository, times(1)).save(captor.capture());
        KnownChampion savedObject = captor.getValue();
        assertEquals(NAME, savedObject.getName());
    }

    @Test
    public void updateDelete() {
        KnownChampionInputDto input = new KnownChampionInputDto();
        input.setName(NAME);

        when(knownChampionRepository.findByName(NAME)).thenReturn(knownChampion);

        knownChampionService.update(input, DBID);
        verify(knownChampionRepository, times(1)).delete(knownChampion);
    }
/*
    @Test
    public void update_ByNewAndOldName_Succeeds() {
        when(knownChampionRepository.findByName(OLD_NAME)).thenReturn(knownChampion);
        knownChampionService.update(NEW_NAME, OLD_NAME);
        assertEquals(NEW_NAME, knownChampion.getName());
        verify(knownChampionRepository, times(1)).save(captor.capture());
        KnownChampion savedObject = captor.getValue();
        assertEquals(knownChampion, savedObject);
    }*/
}