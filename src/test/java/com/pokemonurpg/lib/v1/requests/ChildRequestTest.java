package com.pokemonurpg.lib.v1.requests;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChildRequestTest {
    private Boolean DELETE = true;

    @Test
    public void testPojo() {
        ChildRequest inputDto = new ChildRequest();
        inputDto.setDelete(DELETE);
        assertEquals(DELETE, inputDto.getDelete());
    }

}