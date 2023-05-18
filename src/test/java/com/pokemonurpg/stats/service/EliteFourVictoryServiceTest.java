package com.pokemonurpg.stats.service;

import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.stats.v1.EliteFourVictoryInputDto;
import com.pokemonurpg.entities.v1.EliteFourVictory;
import com.pokemonurpg.infrastructure.v1.data.jpa.EliteFourVictoryRepository;
import com.pokemonurpg.stats.v1.EliteFourVictoryService;
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
public class EliteFourVictoryServiceTest {
    private final static Integer CHALLENGER_DBID = 432;
    private final static String DEFENDER = "DEFENDER";
    private final static Member CHALLENGER = mock(Member.class);
    private final static String LOG_URL = "LOG_URL";
    private final static EliteFourVictory EXISTING_RECORD = mock(EliteFourVictory.class);
    private final static List<EliteFourVictory> VICTORIES = new ArrayList<>();

    @InjectMocks
    private EliteFourVictoryService eliteFourVictoryService;

    @Mock
    private EliteFourVictoryRepository eliteFourVictoryRepository;

    private EliteFourVictoryInputDto input;

    @Captor
    private ArgumentCaptor<EliteFourVictory> captor;

    @Before
    public void init() {
        input = new EliteFourVictoryInputDto();
        input.setDefender(DEFENDER);
        input.setLogUrl(LOG_URL);
    }

    @Test
    public void findAll() {
        when(eliteFourVictoryRepository.findAll()).thenReturn(VICTORIES);
        assertEquals(VICTORIES, eliteFourVictoryService.findAll());
    }
/*
    @Test
    public void updateDeletesARecord() {
        input.setDelete(true);

        when(eliteFourVictoryRepository.findByChallengerAndDefender(CHALLENGER, DEFENDER)).thenReturn(EXISTING_RECORD);

        eliteFourVictoryService.update(input, CHALLENGER);

        verify(eliteFourVictoryRepository, times(1)).delete(EXISTING_RECORD);
    }

    @Test
    public void updateUpdatesARecord() {

        when(eliteFourVictoryRepository.findByChallengerAndDefender(CHALLENGER, DEFENDER)).thenReturn(EXISTING_RECORD);

        eliteFourVictoryService.update(input, CHALLENGER);

        verify(EXISTING_RECORD).update(input);
        verify(eliteFourVictoryRepository).save(EXISTING_RECORD);
    }

    @Test
    public void updateCreatesARecordWhenDefenderIsNull() {
        when(eliteFourVictoryRepository.findByChallengerAndDefender(CHALLENGER, DEFENDER)).thenReturn(null);
        when(CHALLENGER.getDbid()).thenReturn(CHALLENGER_DBID);

        eliteFourVictoryService.update(input, CHALLENGER);

        verify(eliteFourVictoryRepository).save(captor.capture());
        EliteFourVictory savedObject = captor.getValue();
        assertEquals(CHALLENGER, savedObject.getChallenger());
        assertEquals(DEFENDER, savedObject.getDefender());
    }*/
}