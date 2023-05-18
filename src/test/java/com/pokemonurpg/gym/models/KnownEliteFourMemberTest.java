package com.pokemonurpg.gym.models;

import com.pokemonurpg.entities.v1.KnownEliteFourMember;
import org.junit.Test;

import static org.junit.Assert.*;

public class KnownEliteFourMemberTest {
    private final static Integer DBID = 4324;
    private final static String NAME = "NAME";

    @Test
    public void testPojo() {
        KnownEliteFourMember model = new KnownEliteFourMember();
        model.setName(NAME);
        model.setDbid(DBID);

        assertEquals(NAME, model.getName());
        assertEquals(DBID, model.getDbid());
    }

    @Test
    public void testConstructor() {
        KnownEliteFourMember model = new KnownEliteFourMember(NAME);
        assertEquals(NAME, model.getName());
    }
}