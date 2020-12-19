package com.pokemonurpg.gym.models;

import com.pokemonurpg.gym.input.GymInputDto;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.models.EarnedBadge;
import com.pokemonurpg.stats.models.OwnedPokemon;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class GymTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "NAME";
    private final static Member MEMBER = mock(Member.class);
    private final static GymLeague LEAGUE = mock(GymLeague.class);
    private final static Badge BADGE = mock(Badge.class);
    private final static Boolean ACTIVE = true;
    private final static Date DATE = mock(Date.class);
    private final static Integer WINS = 3242;
    private final static Integer LOSSES = 325;
    private final static Integer DRAWS = 567;
    private final static Item TM = mock(Item.class);
    private final static List<EarnedBadge> WINNERS = new ArrayList<>();
    private final static Set<OwnedPokemon> POKEMON = new HashSet<>();

    @Test
    public void testPojo() {
        Gym gym = new Gym();
        gym.setDbid(DBID);
        gym.setName(NAME);
        gym.setOwner(MEMBER);
        gym.setLeague(LEAGUE);
        gym.setBadge(BADGE);
        gym.setActive(ACTIVE);
        gym.setOpenDate(DATE);
        gym.setWins(WINS);
        gym.setLosses(LOSSES);
        gym.setDraws(DRAWS);
        gym.setTm(TM);
        gym.setWinners(WINNERS);
        gym.setPokemon(POKEMON);

        assertEquals(DBID, gym.getDbid());
        assertEquals(NAME, gym.getName());
        assertEquals(MEMBER, gym.getOwner());
        assertEquals(LEAGUE, gym.getLeague());
        assertEquals(BADGE, gym.getBadge());
        assertEquals(ACTIVE, gym.getActive());
        assertEquals(DATE, gym.getOpenDate());
        assertEquals(WINS, gym.getWins());
        assertEquals(LOSSES, gym.getLosses());
        assertEquals(DRAWS, gym.getDraws());
        assertEquals(TM, gym.getTm());
        assertEquals(WINNERS, gym.getWinners());
        assertEquals(POKEMON, gym.getPokemon());
    }

    @Test
    public void testConstructor() {
        GymInputDto input = new GymInputDto();
        input.setName(NAME);
        input.setActive(ACTIVE);
        input.setOpenDate(DATE);
        input.setWins(WINS);
        input.setLosses(LOSSES);
        input.setDraws(DRAWS);

        Gym gym = new Gym(input);
        assertEquals(NAME, gym.getName());
        assertEquals(ACTIVE, gym.getActive());
        assertEquals(DATE, gym.getOpenDate());
        assertEquals(WINS, gym.getWins());
        assertEquals(LOSSES, gym.getLosses());
        assertEquals(DRAWS, gym.getDraws());
    }


}