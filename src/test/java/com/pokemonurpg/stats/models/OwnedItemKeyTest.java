package com.pokemonurpg.stats.models;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class OwnedItemKeyTest {
    private final static Integer TRAINER_DBID = 432;
    private final static Integer ITEM_DBID = 42324;

    @Test
    public void testEqualsAndHashCode() {
        OwnedItemKey key = new OwnedItemKey();

        assertTrue(key.equals(key));
        assertFalse(key.equals(null));
        assertFalse(key.equals(new Object()));

        OwnedItemKey key2 = new OwnedItemKey(TRAINER_DBID, ITEM_DBID);
        OwnedItemKey key3 = new OwnedItemKey(TRAINER_DBID, ITEM_DBID);
        assertTrue(key2.equals(key3));

        assertEquals(Objects.hash(TRAINER_DBID, ITEM_DBID), key2.hashCode());
    }
}