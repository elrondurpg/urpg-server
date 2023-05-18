package com.pokemonurpg.stats.v1;

import com.pokemonurpg.entities.v1.Gym;
import com.pokemonurpg.entities.v1.GymLeague;
import com.pokemonurpg.infrastructure.v1.data.jpa.GymLeagueRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.GymRepository;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.stats.v1.GymVictoryInputDto;
import com.pokemonurpg.entities.v1.GymVictory;
import com.pokemonurpg.infrastructure.v1.data.jpa.GymVictoryRepository;
import com.pokemonurpg.stats.v1.GymVictoryService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

public class GymVictoryServiceTest {
    // TODO Refactor this service as a private method in the stats/v1/member UpdateMemberRequest class
    /*
    private final static Member CHALLENGER = mock(Member.class);
    private final static Integer CHALLENGER_DBID = 432;
    private final static String DEFENDER = "DEFENDER";
    private final static Gym GYM = mock(Gym.class);
    private final static String GYM_NAME = "GYM_NAME";
    private final static GymLeague LEAGUE = mock(GymLeague.class);
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
    private GymLeagueRepository gymLeagueRepository;

    private GymVictoryInputDto input;

    @Captor
    private ArgumentCaptor<GymVictory> captor;

    @Before
    public void init() {
        input = new GymVictoryInputDto();
        input.setDefender(DEFENDER);
        input.setLogUrl(LOG_URL);
        input.setGym(GYM_NAME);
        input.setLeague(LEAGUE_NAME);
    }

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