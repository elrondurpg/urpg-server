package com.pokemonurpg.stats.models;

import com.pokemonurpg.general.models.Section;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.LegendaryProgressInputDto;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class LegendaryProgressTest {
    private final static Integer DBID = 3432;
    private final static Member MEMBER = mock(Member.class);
    private final static Section SECTION = mock(Section.class);
    private final static Integer VALUE = 43242;
    private final static Date DATE = new Date();
    private final static String LOG_URL = "LOG_URL";

    @Test
    public void testPojo() {
        LegendaryProgress legendaryProgress = new LegendaryProgress();
        legendaryProgress.setDbid(DBID);
        assertEquals(DBID, legendaryProgress.getDbid());
    }

    @Test
    public void testConstructor() {
        LegendaryProgressInputDto input = new LegendaryProgressInputDto();
        input.setValue(VALUE);
        input.setDate(DATE);
        input.setLogUrl(LOG_URL);

        LegendaryProgress legendaryProgress = new LegendaryProgress(input, MEMBER, SECTION);
        assertEquals(MEMBER, legendaryProgress.getTrainer());
        assertEquals(SECTION, legendaryProgress.getSection());
        assertEquals(DATE, legendaryProgress.getDate());
        assertEquals(VALUE, legendaryProgress.getValue());
        assertEquals(LOG_URL, legendaryProgress.getLogUrl());
    }
}