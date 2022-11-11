package com.pokemonurpg.contest.input;

import com.pokemonurpg.contest.input.ContestAttributeInputDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContestAttributeInputDtoTest {
    private final static String NAME = "TEST";

    @Test
    public void testPojo() {
        ContestAttributeInputDto input = new ContestAttributeInputDto();
        input.setName(NAME);
        assertEquals(NAME, input.getName());
    }
}