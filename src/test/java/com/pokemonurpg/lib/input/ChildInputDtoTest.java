package com.pokemonurpg.lib.input;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChildInputDtoTest {
    private Boolean DELETE = true;

    @Test
    public void testPojo() {
        ChildInputDto inputDto = new ChildInputDto();
        inputDto.setDelete(DELETE);
        assertEquals(DELETE, inputDto.getDelete());
    }

}