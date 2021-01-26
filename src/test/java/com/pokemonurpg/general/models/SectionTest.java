package com.pokemonurpg.general.models;

import com.pokemonurpg.general.input.SectionInputDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class SectionTest {
    private final static Integer DBID = 3242;
    private final static String NAME = "NAME";
    private final static Integer TIER1_LEGENDARY_REQUIREMENT = 342;
    private final static Integer TIER2_LEGENDARY_REQUIREMENT = 2342;

    @Test
    public void testPojo() {
        Section section = new Section();
        section.setDbid(DBID);
        assertEquals(DBID, section.getDbid());
    }

    @Test
    public void testConstructor() {
        SectionInputDto input = new SectionInputDto();
        input.setName(NAME);
        input.setTier1LegendaryRequirement(TIER1_LEGENDARY_REQUIREMENT);
        input.setTier2LegendaryRequirement(TIER2_LEGENDARY_REQUIREMENT);

        Section section = new Section(input);
        assertEquals(NAME, section.getName());
        assertEquals(TIER1_LEGENDARY_REQUIREMENT, section.getTier1LegendaryRequirement());
        assertEquals(TIER2_LEGENDARY_REQUIREMENT, section.getTier2LegendaryRequirement());
    }

}