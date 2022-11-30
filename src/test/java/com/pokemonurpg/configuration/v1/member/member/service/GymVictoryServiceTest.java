package com.pokemonurpg.configuration.v1.member.member.service;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.configuration.v1.gym.gym.repository.GymRepository;
import com.pokemonurpg.configuration.v1.gym.league.model.League;
import com.pokemonurpg.configuration.v1.gym.league.repository.LeagueRepository;
import com.pokemonurpg.configuration.v1.member.member.input.GymVictoryInputDto;
import com.pokemonurpg.configuration.v1.member.member.model.GymVictory;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.stats.repository.GymVictoryRepository;

@ExtendWith(MockitoExtension.class)
public class GymVictoryServiceTest {
    private final static Member CHALLENGER = mock(Member.class);
    private final static Integer CHALLENGER_DBID = 432;
    private final static String DEFENDER = "DEFENDER";
    private final static Gym GYM = mock(Gym.class);
    private final static String GYM_NAME = "GYM_NAME";
    private final static League LEAGUE = mock(League.class);
    private final static String LEAGUE_NAME = "LEAGUE_NAME";
    private final static String LOG_URL = "LOG_URL";
    private final static GymVictory EXISTING_RECORD = mock(GymVictory.class);

    @InjectMocks
    private GymVictoryService championVictoryService;

    @Mock
    private GymVictoryRepository championVictoryRepository;

    @Mock
    private GymRepository gymRepository;

    @Mock
    private LeagueRepository gymLeagueRepository;

    private GymVictoryInputDto input;

    @Captor
    private ArgumentCaptor<GymVictory> captor;

    @BeforeEach
    public void init() {
        input = new GymVictoryInputDto();
        input.setDefender(DEFENDER);
        input.setLogUrl(LOG_URL);
        input.setGym(GYM_NAME);
        input.setLeague(LEAGUE_NAME);
    }
/*
    @Test
    public void updateDeletesARecord() {
        when(gymRepository.findByName(GYM_NAME)).thenReturn(GYM);
        when(gymLeagueRepository.findByName(LEAGUE_NAME)).thenReturn(LEAGUE);
        when(championVictoryRepository.findByChallengerAndDefenderAndGymAndLeague(CHALLENGER, DEFENDER, GYM, LEAGUE)).thenReturn(EXISTING_RECORD);

        input.setDelete(true);
        championVictoryService.update(input, CHALLENGER);

        verify(championVictoryRepository, times(1)).delete(EXISTING_RECORD);
    }

    @Test
    public void updateUpdatesARecord() {
        when(gymRepository.findByName(GYM_NAME)).thenReturn(GYM);
        when(gymLeagueRepository.findByName(LEAGUE_NAME)).thenReturn(LEAGUE);
        when(championVictoryRepository.findByChallengerAndDefenderAndGymAndLeague(CHALLENGER, DEFENDER, GYM, LEAGUE)).thenReturn(EXISTING_RECORD);

        championVictoryService.update(input, CHALLENGER);

        verify(EXISTING_RECORD).update(input);
        verify(championVictoryRepository).save(EXISTING_RECORD);
    }

    @Test
    public void updateCreatesARecordWhenDefenderIsNull() {
        when(gymRepository.findByName(GYM_NAME)).thenReturn(GYM);
        when(gymLeagueRepository.findByName(LEAGUE_NAME)).thenReturn(LEAGUE);
        when(championVictoryRepository.findByChallengerAndDefenderAndGymAndLeague(CHALLENGER, DEFENDER, GYM, LEAGUE)).thenReturn(null);

        championVictoryService.update(input, CHALLENGER);

        verify(championVictoryRepository).save(captor.capture());
        GymVictory savedObject = captor.getValue();
        assertEquals(CHALLENGER, savedObject.getChallenger());
        assertEquals(DEFENDER, savedObject.getDefender());
        assertEquals(GYM, savedObject.getGym());
        assertEquals(LEAGUE, savedObject.getLeague());
    }*/
}