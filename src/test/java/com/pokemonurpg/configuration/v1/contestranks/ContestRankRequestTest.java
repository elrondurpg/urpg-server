package com.pokemonurpg.configuration.v1.contestranks;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContestRankRequestTest {
    private final static String NAME = "TEST";

    @Test
    public void testPojo() {
        ContestRankRequest input = new ContestRankRequest();
        input.setName(NAME);
        assertEquals(NAME, input.getName());
    }
}