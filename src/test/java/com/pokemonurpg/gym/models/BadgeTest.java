package com.pokemonurpg.gym.models;

import com.pokemonurpg.configuration.v1.badges.BadgeInputDto;
import com.pokemonurpg.entities.Badge;
import org.junit.Test;

import static org.junit.Assert.*;

public class BadgeTest {
    private final static Integer DBID = 324;
    private final static String NAME = "NAME";

    @Test
    public void testPojo() {
        Badge badge = new Badge();
        badge.setDbid(DBID);
        badge.setName(NAME);

        assertEquals(DBID, badge.getDbid());
        assertEquals(NAME, badge.getName());
    }

    @Test
    public void testConstructor() {
        BadgeInputDto input = new BadgeInputDto();
        input.setName(NAME);

        Badge badge = new Badge(input);
        assertEquals(NAME, badge.getName());
    }

}