package com.pokemonurpg.core.input;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChildInputDtoTest {
    private Boolean DELETE = true;

    @Test
    public void testPojo() {
        ChildInputDto inputDto = new ChildInputDto();
        inputDto.setDelete(DELETE);
        assertEquals(DELETE, inputDto.getDelete());
    }

}