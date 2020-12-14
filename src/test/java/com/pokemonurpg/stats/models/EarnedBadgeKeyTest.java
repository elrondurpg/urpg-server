package com.pokemonurpg.stats.models;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class EarnedBadgeKeyTest {
    public static final Integer TRAINER_DBID = 432;
    public static final Integer GYM_DBID = 432123;

    @Test
    public void testEqualsEmptyObject() {
        EarnedBadgeKey key = new EarnedBadgeKey();

        assertTrue(key.equals(key));
        assertFalse(key.equals(null));
        assertFalse(key.equals(new Object()));
    }

    @Test
    public void testEquals() {
        EarnedBadgeKey key = new EarnedBadgeKey(TRAINER_DBID, GYM_DBID);
        EarnedBadgeKey key2 = new EarnedBadgeKey(TRAINER_DBID, GYM_DBID);

        assertTrue(key.equals(key2));
    }

    @Test
    public void testHashCode() {
        EarnedBadgeKey key = new EarnedBadgeKey(TRAINER_DBID, GYM_DBID);
        assertEquals(Objects.hash(TRAINER_DBID, GYM_DBID), key.hashCode());
    }

}