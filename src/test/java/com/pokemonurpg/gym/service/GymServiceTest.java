package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.input.GymInputDto;
import com.pokemonurpg.gym.models.Badge;
import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.models.GymLeague;
import com.pokemonurpg.gym.repository.GymRepository;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.item.service.ItemService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GymServiceTest {
    private final static List<Gym> GYMS = new ArrayList<>();
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";
    private final static String OWNER_NAME = "OWNER";
    private final static String LEAGUE_NAME = "LEAGUE";
    private final static String BADGE_NAME = "BADGE";
    private final static String TM_NAME = "TM";
    private final static Member MEMBER = mock(Member.class);
    private final static GymLeague LEAGUE = mock(GymLeague.class);
    private final static Badge BADGE = mock(Badge.class);
    private final static Item TM = mock(Item.class);

    @InjectMocks
    private GymService gymService;

    @Mock
    private GymRepository gymRepository;

    @Mock
    private MemberService memberService;

    @Mock
    private GymLeagueService gymLeagueService;

    @Mock
    private BadgeService badgeService;

    @Mock
    private ItemService itemService;

    @Mock
    private GymPokemonService gymPokemonService;

    private Gym gym = new Gym();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        when(gymRepository.findAll()).thenReturn(GYMS);
        assertEquals(GYMS, gymService.findAll());
    }

    @Test
    public void findByDbid() {
        when(gymRepository.findByDbid(DBID)).thenReturn(gym);
        assertEquals(gym, gymService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(gymRepository.findFirstByNameAndActiveIsTrue(NAME)).thenReturn(gym);
        assertEquals(gym, gymService.findFirstActiveByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(gymRepository.findFirstByNameAndActiveIsTrue(NAME)).thenReturn(null);
        when(gymRepository.findFirstByNameStartingWithAndActiveIsTrue(NAME)).thenReturn(gym);
        assertEquals(gym, gymService.findFirstActiveByName(NAME));
    }

    @Test
    public void create() {
        GymInputDto input = new GymInputDto();
        input.setName(NAME);

        Gym gym = gymService.create(input);
        assertEquals(NAME, gym.getName());
        verify(gymRepository, times(1)).save(gym);
        verify(gymPokemonService, times(1)).updateAll(input, gym);
    }

    @Test
    public void updateExistingRecord() {
        GymInputDto input = new GymInputDto();
        input.setName(NAME);
        input.setLeague(LEAGUE_NAME);
        input.setOwner(OWNER_NAME);
        input.setBadge(BADGE_NAME);
        input.setTm(TM_NAME);

        when(gymRepository.findByDbid(DBID)).thenReturn(gym);
        when(memberService.findByName(OWNER_NAME)).thenReturn(MEMBER);
        when(gymLeagueService.findByName(LEAGUE_NAME)).thenReturn(LEAGUE);
        when(badgeService.findByName(BADGE_NAME)).thenReturn(BADGE);
        when(itemService.findByName(TM_NAME)).thenReturn(TM);

        Gym gym1 = gymService.update(input, DBID);
        assertEquals(gym, gym1);
        assertEquals(NAME, gym1.getName());
        assertEquals(MEMBER, gym1.getOwner());
        assertEquals(LEAGUE, gym1.getLeague());
        assertEquals(BADGE, gym1.getBadge());
        assertEquals(TM, gym1.getTm());
        verify(gymRepository, times(1)).save(gym1);
        verify(gymPokemonService, times(1)).updateAll(input, gym1);
    }

    @Test
    public void updateNonExistingRecord() {
        GymInputDto input = new GymInputDto();
        input.setName(NAME);

        when(gymRepository.findByDbid(DBID)).thenReturn(null);

        Gym gym1 = gymService.update(input, DBID);
        assertNull(gym1);
        verify(gymRepository, times(0)).save(Matchers.any());
    }

}