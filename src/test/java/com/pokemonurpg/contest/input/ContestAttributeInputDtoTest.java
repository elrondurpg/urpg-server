package com.pokemonurpg.contest.input;

import com.pokemonurpg.contest.input.ContestAttributeInputDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContestAttributeInputDtoTest {
    private final static String NAME = "TEST";

    @Test
    public void testPojo() {
        ContestAttributeInputDto input = new ContestAttributeInputDto();
        input.setName(NAME);
        assertEquals(NAME, input.getName());
    }
}