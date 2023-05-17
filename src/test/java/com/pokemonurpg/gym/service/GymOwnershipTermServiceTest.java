package com.pokemonurpg.gym.service;

import com.pokemonurpg.configuration.v1.gymleaderrecords.GymOwnershipTermInputDto;
import com.pokemonurpg.entities.Gym;
import com.pokemonurpg.entities.GymLeague;
import com.pokemonurpg.entities.GymOwnershipTerm;
import com.pokemonurpg.infrastructure.data.GymOwnershipTermRepository;
import com.pokemonurpg.configuration.v1.gymleagues.GymLeagueService;
import com.pokemonurpg.configuration.v1.gymleaderrecords.GymOwnershipTermService;
import com.pokemonurpg.configuration.v1.gyms.GymService;
import com.pokemonurpg.configuration.v1.gymleaders.KnownGymLeaderService;
import com.pokemonurpg.entities.Item;
import com.pokemonurpg.configuration.v1.items.ItemService;
import com.pokemonurpg.entities.Member;
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
public class GymOwnershipTermServiceTest {
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

    private                 GymOwnershipTerm    term        = new GymOwnershipTerm();

    @InjectMocks
    private GymOwnershipTermService gymOwnershipTermService;

    @Mock
    private GymOwnershipTermRepository gymOwnershipTermRepository;

    @Mock
    private GymService gymService;

    @Mock
    private GymLeagueService gymLeagueService;

    @Mock
    private ItemService itemService;

    @Mock
    private MemberService memberService;

    @Mock
    private KnownGymLeaderService knownGymLeaderService;

    @Captor
    ArgumentCaptor<GymOwnershipTerm> termCaptor;

    @Captor
    ArgumentCaptor<Integer> intCaptor;

    @Before
    public void init () {
        gym = mock(Gym.class);
    }

    @Test
    public void findByDbid() {
        when(gymOwnershipTermRepository.findByDbid(DBID)).thenReturn(term);
        assertEquals(term, gymOwnershipTermService.findByDbid(DBID));
    }

    @Test
    public void findByGymAndOwnerAndOpenDate() {
        when(gymService.findByName(GYM_NAME)).thenReturn(gym);
        when(memberService.findByNameExact(OWNER_NAME)).thenReturn(OWNER);

        when(gymOwnershipTermRepository.findByGymAndOwnerAndOpenDate(gym, OWNER, DATE)).thenReturn(term);
        assertEquals(term, gymOwnershipTermService.findByGymAndOwnerAndOpenDate(GYM_NAME, OWNER_NAME, DATE));
    }

    @Test
    public void create() {
        when(gymService.findByName(GYM_NAME)).thenReturn(gym);
        when(memberService.findByNameExact(OWNER_NAME)).thenReturn(OWNER);
        when(gymLeagueService.findByName(LEAGUE_NAME)).thenReturn(LEAGUE);
        when(itemService.findByName(TM_NAME)).thenReturn(TM);

        GymOwnershipTermInputDto input = new GymOwnershipTermInputDto();
        input.setOpenDate(DATE);
        input.setGym(GYM_NAME);
        input.setOwner(OWNER_NAME);
        input.setTm(TM_NAME);
        input.setLeague(LEAGUE_NAME);
        input.setBecomeCurrentOwner(true);

        GymOwnershipTerm term = gymOwnershipTermService.create(input);
        verify(gymOwnershipTermRepository,
                times(1)).save(termCaptor.capture());

        GymOwnershipTerm savedObject = termCaptor.getValue();
        assertEquals(term, savedObject);
        assertEquals(DATE, term.getOpenDate());
        assertEquals(OWNER, term.getOwner());
        assertEquals(gym, term.getGym());
        assertEquals(LEAGUE, term.getLeague());
        assertEquals(TM, term.getTm());

        verify(gymService, times(1)).updateCurrentOwnerRecord(gym, term);
        verify(knownGymLeaderService, times(1)).create(OWNER_NAME);

    }

    @Test
    public void update() {
        term.setGym(gym);
        when(gymOwnershipTermRepository.findByDbid(DBID)).thenReturn(term);
        when(gymLeagueService.findByName(LEAGUE_NAME)).thenReturn(LEAGUE);
        when(itemService.findByName(TM_NAME)).thenReturn(TM);

        GymOwnershipTermInputDto input = new GymOwnershipTermInputDto();
        input.setTm(TM_NAME);
        input.setLeague(LEAGUE_NAME);
        input.setBecomeCurrentOwner(true);

        GymOwnershipTerm term = gymOwnershipTermService.update(input, DBID);
        verify(gymOwnershipTermRepository,
                times(1)).save(termCaptor.capture());

        GymOwnershipTerm savedObject = termCaptor.getValue();
        assertEquals(term, savedObject);
        assertEquals(LEAGUE, term.getLeague());
        assertEquals(TM, term.getTm());

        verify(gymService, times(1)).updateCurrentOwnerRecord(gym, term);
    }

    @Test
    public void delete() {
        when(gymOwnershipTermRepository.findByDbid(DBID)).thenReturn(term);
        when(gymService.findByCurrentOwnerRecord(term)).thenReturn(gym);

        gymOwnershipTermService.delete(DBID);

        verify(gym, times(1)).setCurrentOwnerRecord(termCaptor.capture());
        assertNull(termCaptor.getValue());

        verify(gymOwnershipTermRepository, times(1)).deleteByDbid(intCaptor.capture());
        assertEquals(DBID, intCaptor.getValue());
    }

}