package com.pokemonurpg.stats.service;

import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.stats.v1.ChampionVictoryInputDto;
import com.pokemonurpg.entities.v1.ChampionVictory;
import com.pokemonurpg.infrastructure.v1.data.jpa.ChampionVictoryRepository;
import com.pokemonurpg.stats.v1.ChampionVictoryService;
import org.junit.Before;
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
public class ChampionVictoryServiceTest {
    private final static Integer CHALLENGER_DBID = 432;
    private final static String DEFENDER = "DEFENDER";
    private final static Member CHALLENGER = mock(Member.class);
    private final static String LOG_URL = "LOG_URL";
    private final static ChampionVictory EXISTING_RECORD = mock(ChampionVictory.class);
    private final static List<ChampionVictory> VICTORIES = new ArrayList<>();

    @InjectMocks
    private ChampionVictoryService championVictoryService;

    @Mock
    private ChampionVictoryRepository championVictoryRepository;

    private ChampionVictoryInputDto input;

    @Captor
    private ArgumentCaptor<ChampionVictory> captor;

    @Before
    public void init() {
        input = new ChampionVictoryInputDto();
        input.setDefender(DEFENDER);
        input.setLogUrl(LOG_URL);
    }

    @Test
    public void findAll() {
        when(championVictoryRepository.findAll()).thenReturn(VICTORIES);
        assertEquals(VICTORIES, championVictoryService.findAll());
    }

    /*@Test
    public void updateDeletesARecord() {
        input.setDelete(true);

        when(championVictoryRepository.findByChallengerAndDefender(CHALLENGER, DEFENDER)).thenReturn(EXISTING_RECORD);

        championVictoryService.update(input, CHALLENGER);

        verify(championVictoryRepository, times(1)).delete(EXISTING_RECORD);
    }

    @Test
    public void updateUpdatesARecord() {
        when(championVictoryRepository.findByChallengerAndDefender(CHALLENGER, DEFENDER)).thenReturn(EXISTING_RECORD);

        championVictoryService.update(input, CHALLENGER);

        verify(EXISTING_RECORD).update(input);
        verify(championVictoryRepository).save(EXISTING_RECORD);
    }

    @Test
    public void updateCreatesARecordWhenDefenderIsNull() {
        when(championVictoryRepository.findByChallengerAndDefender(CHALLENGER, DEFENDER)).thenReturn(null);
        when(CHALLENGER.getDbid()).thenReturn(CHALLENGER_DBID);

        championVictoryService.update(input, CHALLENGER);

        verify(championVictoryRepository).save(captor.capture());
        ChampionVictory savedObject = captor.getValue();
        assertEquals(CHALLENGER, savedObject.getChallenger());
        assertEquals(DEFENDER, savedObject.getDefender());
    }*/
}