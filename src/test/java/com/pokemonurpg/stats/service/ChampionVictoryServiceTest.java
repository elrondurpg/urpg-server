package com.pokemonurpg.stats.service;

import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.ChampionVictoryInputDto;
import com.pokemonurpg.stats.models.ChampionVictory;
import com.pokemonurpg.stats.repository.ChampionVictoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChampionVictoryServiceTest {
    private final static Integer CHALLENGER_DBID = 432;
    private final static String DEFENDER = "DEFENDER";
    private final static Member CHALLENGER = mock(Member.class);
    private final static String LOG_URL = "LOG_URL";
    private final static ChampionVictory EXISTING_RECORD = mock(ChampionVictory.class);

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
    }
}