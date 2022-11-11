package com.pokemonurpg.contest.models;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class ContestComboKeyTest {
    private final static Integer FIRST_ATTACK_DBID = 32432;
    private final static Integer SECOND_ATTACK_DBID = 23422;
    private final static String CONTEST_TYPE = "RSE";

    @Test
    public void testConstructor() {
        ContestComboKey contestComboKey = new ContestComboKey();
        assertNotNull(contestComboKey);
    }
/*
    @Test
    public void testPojo() {
        ContestComboKey contestComboKey = new ContestComboKey(FIRST_ATTACK_DBID, SECOND_ATTACK_DBID, CONTEST_TYPE);
        assertEquals(FIRST_ATTACK_DBID, contestComboKey.getFirstAttackDbid());
        assertEquals(SECOND_ATTACK_DBID, contestComboKey.getSecondAttackDbid());
        assertEquals(CONTEST_TYPE, contestComboKey.getContestType());
    }

    @Test
    public void testEqualsAndHashCode() {
        ContestComboKey key1 = new ContestComboKey(FIRST_ATTACK_DBID, SECOND_ATTACK_DBID, CONTEST_TYPE);
        ContestComboKey key2 = new ContestComboKey(FIRST_ATTACK_DBID, SECOND_ATTACK_DBID, CONTEST_TYPE);

        assertEquals(key1, key1);

        assertNotEquals(key1, new Object());

        assertEquals(key1, key2);

        assertEquals(Objects.hash(FIRST_ATTACK_DBID, SECOND_ATTACK_DBID, CONTEST_TYPE), key1.hashCode());
    }
*/
}