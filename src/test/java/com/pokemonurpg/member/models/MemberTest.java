package com.pokemonurpg.member.models;

import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.member.input.MemberInputDto;
import com.pokemonurpg.stats.models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MemberTest {
    private final static Integer DBID = 2432;
    private final static String DISCORD_ID = "ID";
    private final static String USERNAME = "USERNAME";
    private final static Integer SALT = 32432;
    private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final static String REFRESH_TOKEN = "REFRESH_TOKEN";
    private final static Long SESSION_EXPIRE = 342L;
    private final static Integer MONEY = 34531;
    private final static Integer WINS = 3242;
    private final static Integer LOSSES = 34321;
    private final static Integer DRAWS = 64231432;
    private final static Date JOIN_DATE = new Date();
    private final static List<OwnedPokemon> POKEMON = new ArrayList<>();
    private final static List<OwnedItem> ITEMS = new ArrayList<>();
    private final static List<LegendaryProgress> LEGENDARY_PROGRESS = new ArrayList<>();
    private final static Set<Role> ROLES = new HashSet<>();
    private final static Boolean BANNED = true;
    private final static Date BAN_EXPIRATION = new Date();
    private final static Set<Gym> GYMS = new HashSet<>();
    private final static Boolean BOT = true;
    private final static List<EliteFourVictory> ELITE_FOUR_VICTORIES = new ArrayList<>();
    private final static List<ChampionVictory> CHAMPION_VICTORIES = new ArrayList<>();
    private final static List<GymVictory> GYM_VICTORIES = new ArrayList<>();

    private final static Integer OTHER_DBID = 432432;
    private final static Member MEMBER_WITH_DBID = new Member();
    private final static EliteFourVictory VICTORY_WITH_DBID = new EliteFourVictory();
    private final static String DEFENDER_NAME = "DEFENDER_NAME";
    private final static EliteFourVictory VICTORY_WITH_NAME = new EliteFourVictory();
    private final static Boolean IS_CHAMPION = true;

    @Before
    public void init() {
        MEMBER_WITH_DBID.setDbid(OTHER_DBID);
    }

    @Test
    public void testPojo() {
        Member member = new Member();
        member.setDbid(DBID);
        member.setDiscordId(DISCORD_ID);
        member.setUsername(USERNAME);
        member.setSalt(SALT);
        member.setAccessToken(ACCESS_TOKEN);
        member.setRefreshToken(REFRESH_TOKEN);
        member.setSessionExpire(SESSION_EXPIRE);
        member.setMoney(MONEY);
        member.setWins(WINS);
        member.setLosses(LOSSES);
        member.setDraws(DRAWS);
        member.setJoinDate(JOIN_DATE);
        member.setPokemon(POKEMON);
        member.setItems(ITEMS);
        member.setLegendaryProgress(LEGENDARY_PROGRESS);
        member.setRoles(ROLES);
        member.setBanned(BANNED);
        member.setBanExpiration(BAN_EXPIRATION);
        member.setGyms(GYMS);
        member.setBot(BOT);
        member.setEliteFourVictories(ELITE_FOUR_VICTORIES);
        member.setChampionVictories(CHAMPION_VICTORIES);
        member.setGymVictories(GYM_VICTORIES);

        assertEquals(DBID,member.getDbid());
        assertEquals(DISCORD_ID,member.getDiscordId());
        assertEquals(USERNAME,member.getUsername());
        assertEquals(SALT,member.getSalt());
        assertEquals(ACCESS_TOKEN,member.getAccessToken());
        assertEquals(REFRESH_TOKEN,member.getRefreshToken());
        assertEquals(SESSION_EXPIRE,member.getSessionExpire());
        assertEquals(MONEY,member.getMoney());
        assertEquals(WINS,member.getWins());
        assertEquals(LOSSES,member.getLosses());
        assertEquals(DRAWS,member.getDraws());
        assertEquals(JOIN_DATE,member.getJoinDate());
        assertEquals(POKEMON,member.getPokemon());
        assertEquals(ITEMS,member.getItems());
        assertEquals(LEGENDARY_PROGRESS,member.getLegendaryProgress());
        assertEquals(ROLES,member.getRoles());
        assertEquals(BANNED, member.getBanned());
        assertEquals(BAN_EXPIRATION, member.getBanExpiration());
        assertEquals(GYMS, member.getGyms());
        assertEquals(BOT, member.getBot());
        assertEquals(ELITE_FOUR_VICTORIES, member.getEliteFourVictories());
        assertEquals(CHAMPION_VICTORIES, member.getChampionVictories());
        assertEquals(GYM_VICTORIES, member.getGymVictories());
    }

    @Test
    public void testConstructor() {
        MemberInputDto input = new MemberInputDto();
        input.setDiscordId(DISCORD_ID);
        input.setUsername(USERNAME);
        input.setMoney(MONEY);
        input.setWins(WINS);
        input.setLosses(LOSSES);
        input.setDraws(DRAWS);
        input.setJoinDate(JOIN_DATE);
        input.setBot(BOT);

        Member member = new Member(input);

        assertEquals(DISCORD_ID,member.getDiscordId());
        assertEquals(USERNAME,member.getUsername());
        assertEquals(MONEY,member.getMoney());
        assertEquals(WINS,member.getWins());
        assertEquals(LOSSES,member.getLosses());
        assertEquals(DRAWS,member.getDraws());
        assertEquals(JOIN_DATE,member.getJoinDate());
        assertEquals(BOT, member.getBot());
    }

}