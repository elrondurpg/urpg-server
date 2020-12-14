package com.pokemonurpg.pokedex.models;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class TypeMatchupKeyTest {
    private final static Integer ATTACK_TYPE_DBID = 46;
    private final static Integer DEFEND_TYPE_DBID = 32432;

    @Test
    public void testBlankConstructor() {
        TypeMatchupKey typeMatchupKey = new TypeMatchupKey();
        assertNotNull(typeMatchupKey);
    }

    @Test
    public void testConstructor() {
        TypeMatchupKey typeMatchupKey = new TypeMatchupKey(ATTACK_TYPE_DBID, DEFEND_TYPE_DBID);
        assertEquals(ATTACK_TYPE_DBID, typeMatchupKey.getAttackTypeDbid());
        assertEquals(DEFEND_TYPE_DBID, typeMatchupKey.getDefendTypeDbid());
    }

    @Test
    public void testEqualsAndHashCode() {
        TypeMatchupKey typeMatchupKey1 = new TypeMatchupKey(ATTACK_TYPE_DBID, DEFEND_TYPE_DBID);
        TypeMatchupKey typeMatchupKey2 = new TypeMatchupKey(ATTACK_TYPE_DBID, DEFEND_TYPE_DBID);

        assertEquals(typeMatchupKey1, typeMatchupKey1);
        assertEquals(typeMatchupKey1, typeMatchupKey2);
        assertNotEquals(typeMatchupKey1, new Object());
        assertEquals(Objects.hash(ATTACK_TYPE_DBID, DEFEND_TYPE_DBID), typeMatchupKey1.hashCode());
    }
}