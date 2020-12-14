package com.pokemonurpg.stats.models;

import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.EarnedBadgeInputDto;
import org.junit.Test;

import java.util.Date;
import java.util.Objects;

import static org.junit.Assert.*;

public class EarnedBadgeTest {
    private final static Integer MEMBER_DBID = 3242;
    private final static Integer GYM_DBID = 432;
    private final static Date DATE = new Date();
    private final static String LOG_URL = "LOG_URL";

    @Test
    public void testPojo() {
        assertNotNull(new EarnedBadge());
    }

    @Test
    public void testConstructor() {
        EarnedBadgeInputDto input = new EarnedBadgeInputDto();
        input.setDate(DATE);
        input.setLogUrl(LOG_URL);

        Member member = new Member();
        member.setDbid(MEMBER_DBID);

        Gym gym = new Gym();
        gym.setDbid(GYM_DBID);

        EarnedBadge earnedBadge = new EarnedBadge(input, member, gym);
        assertEquals(member, earnedBadge.getMember());
        assertEquals(gym, earnedBadge.getGym());
        assertEquals(DATE, earnedBadge.getDate());
        assertEquals(LOG_URL, earnedBadge.getLogUrl());

        EarnedBadgeKey id = earnedBadge.id;
        assertEquals(MEMBER_DBID, id.getTrainerDbid());
        assertEquals(GYM_DBID, id.getGymDbid());
    }

}