package com.pokemonurpg.gym.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class KnownEliteFourMemberTest {
    private final static String NAME = "NAME";

    @Test
    public void testPojo() {
        KnownEliteFourMember model = new KnownEliteFourMember();
        model.setName(NAME);

        assertEquals(NAME, model.getName());
    }

    @Test
    public void testConstructor() {
        KnownEliteFourMember model = new KnownEliteFourMember(NAME);
        assertEquals(NAME, model.getName());
    }
}