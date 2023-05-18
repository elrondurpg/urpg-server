package com.pokemonurpg.configuration.v1.champions;

import com.pokemonurpg.entities.v1.Champion;
import com.pokemonurpg.infrastructure.v1.data.jpa.ChampionRepository;
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
public class ChampionSlotSlotServiceTest {
    private final static Integer DBID = 432;
    private final static List<String> ALL_NAMES = new ArrayList<>();
    private final static String NAME = "NAME";
    private final static String NEW_NAME = "NEW_NAME";
    private final static String OLD_NAME = "OLD_NAME";

    private Champion champion = new Champion();

    @InjectMocks
    private ChampionService championService;

    @Mock
    private ChampionRepository championRepository;

    @Captor
    ArgumentCaptor<Champion> captor;

    @Test
    public void findAllNames() {
        when(championRepository.findAllNames()).thenReturn(ALL_NAMES);
        assertEquals(ALL_NAMES, championService.findAllNames());
    }

    @Test
    public void findByName() {
        when(championRepository.findByName(NAME)).thenReturn(champion);
        assertEquals(champion, championService.findByName(NAME));
    }

    @Test
    public void findFirstByNameStartingWith() {
        when(championRepository.findFirstByNameStartingWith(NAME)).thenReturn(champion);
        assertEquals(champion, championService.findByName(NAME));
    }

    @Test
    public void create() {
        when(championRepository.findByName(NAME)).thenReturn(null);
        championService.create(NAME);

        verify(championRepository, times(1)).save(captor.capture());
        Champion savedObject = captor.getValue();
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