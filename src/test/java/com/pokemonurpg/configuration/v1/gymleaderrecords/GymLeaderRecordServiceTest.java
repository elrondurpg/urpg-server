package com.pokemonurpg.configuration.v1.gymleaderrecords;

import com.pokemonurpg.entities.v1.Gym;
import com.pokemonurpg.entities.v1.GymLeague;
import com.pokemonurpg.entities.v1.GymLeaderRecord;
import com.pokemonurpg.infrastructure.v1.data.jpa.GymLeaderRecordRepository;
import com.pokemonurpg.configuration.v1.gymleagues.GymLeagueService;
import com.pokemonurpg.configuration.v1.gyms.GymService;
import com.pokemonurpg.configuration.v1.gymleaders.GymLeaderService;
import com.pokemonurpg.entities.v1.Item;
import com.pokemonurpg.configuration.v1.items.ItemService;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GymLeaderRecordServiceTest {
    private final static    Date                DATE        = new Date();
    private       static    Gym                 gym;
    private final static    GymLeague           LEAGUE      = mock(GymLeague.class);
    private final static    Integer             DBID        = 432;
    private final static    Item                TM          = mock(Item.class);
    private final static    Member              OWNER       = mock(Member.class);
    private final static    String              GYM_NAME    = "GYM_NAME";
    private final static    String              LEAGUE_NAME = "LEAGUE_NAME";
    private final static    String              OWNER_NAME  = "OWNER_NAME";
    private final static    String              TM_NAME     = "TM_NAME";

    private GymLeaderRecord term        = new GymLeaderRecord();

    @InjectMocks
    private GymLeaderRecordService gymLeaderRecordService;

    @Mock
    private GymLeaderRecordRepository gymLeaderRecordRepository;

    @Mock
    private GymService gymService;

    @Mock
    private GymLeagueService gymLeagueService;

    @Mock
    private ItemService itemService;

    @Mock
    private MemberService memberService;

    @Mock
    private GymLeaderService gymLeaderService;

    @Captor
    ArgumentCaptor<GymLeaderRecord> termCaptor;

    @Captor
    ArgumentCaptor<Integer> intCaptor;

    @Before
    public void init () {
        gym = mock(Gym.class);
    }

    @Test
    public void findByDbid() {
        when(gymLeaderRecordRepository.findByDbid(DBID)).thenReturn(term);
        assertEquals(term, gymLeaderRecordService.findByDbid(DBID));
    }

    @Test
    public void findByGymAndOwnerAndOpenDate() {
        when(gymService.findByName(GYM_NAME)).thenReturn(gym);
        when(memberService.findByNameExact(OWNER_NAME)).thenReturn(OWNER);

        when(gymLeaderRecordRepository.findByGymAndOwnerAndOpenDate(gym, OWNER, DATE)).thenReturn(term);
        assertEquals(term, gymLeaderRecordService.findByGymAndOwnerAndOpenDate(GYM_NAME, OWNER_NAME, DATE));
    }

    @Test
    public void create() {
        when(gymService.findByName(GYM_NAME)).thenReturn(gym);
        when(memberService.findByNameExact(OWNER_NAME)).thenReturn(OWNER);
        when(gymLeagueService.findByName(LEAGUE_NAME)).thenReturn(LEAGUE);
        when(itemService.findByName(TM_NAME)).thenReturn(TM);

        GymLeaderRecordRequest input = new GymLeaderRecordRequest();
        input.setOpenDate(DATE);
        input.setGym(GYM_NAME);
        input.setOwner(OWNER_NAME);
        input.setTm(TM_NAME);
        input.setLeague(LEAGUE_NAME);
        input.setBecomeCurrentOwner(true);

        GymLeaderRecord term = gymLeaderRecordService.create(input);
        verify(gymLeaderRecordRepository,
                times(1)).save(termCaptor.capture());

        GymLeaderRecord savedObject = termCaptor.getValue();
        assertEquals(term, savedObject);
        assertEquals(DATE, term.getOpenDate());
        assertEquals(OWNER, term.getOwner());
        assertEquals(gym, term.getGym());
        assertEquals(LEAGUE, term.getLeague());
        assertEquals(TM, term.getTm());

        verify(gymService, times(1)).updateCurrentOwnerRecord(gym, term);
        verify(gymLeaderService, times(1)).create(OWNER_NAME);

    }

    @Test
    public void update() {
        term.setGym(gym);
        when(gymLeaderRecordRepository.findByDbid(DBID)).thenReturn(term);
        when(gymLeagueService.findByName(LEAGUE_NAME)).thenReturn(LEAGUE);
        when(itemService.findByName(TM_NAME)).thenReturn(TM);

        GymLeaderRecordRequest input = new GymLeaderRecordRequest();
        input.setTm(TM_NAME);
        input.setLeague(LEAGUE_NAME);
        input.setBecomeCurrentOwner(true);

        GymLeaderRecord term = gymLeaderRecordService.update(input, DBID);
        verify(gymLeaderRecordRepository,
                times(1)).save(termCaptor.capture());

        GymLeaderRecord savedObject = termCaptor.getValue();
        assertEquals(term, savedObject);
        assertEquals(LEAGUE, term.getLeague());
        assertEquals(TM, term.getTm());

        verify(gymService, times(1)).updateCurrentOwnerRecord(gym, term);
    }

    @Test
    public void delete() {
        when(gymLeaderRecordRepository.findByDbid(DBID)).thenReturn(term);
        when(gymService.findByCurrentOwnerRecord(term)).thenReturn(gym);

        gymLeaderRecordService.delete(DBID);

        verify(gym, times(1)).setCurrentOwnerRecord(termCaptor.capture());
        assertNull(termCaptor.getValue());

        verify(gymLeaderRecordRepository, times(1)).deleteByDbid(intCaptor.capture());
        assertEquals(DBID, intCaptor.getValue());
    }

}