package com.pokemonurpg.configuration.v1.champions;

import com.pokemonurpg.entities.v1.KnownChampion;
import com.pokemonurpg.infrastructure.v1.data.jpa.KnownChampionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
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