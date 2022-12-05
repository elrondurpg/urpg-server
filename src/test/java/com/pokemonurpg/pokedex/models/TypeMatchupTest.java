package com.pokemonurpg.pokedex.models;

import org.junit.jupiter.api.Test;

import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.entities.v1.pokemon.TypeMatchup;

import static org.junit.jupiter.api.Assertions.*;

public class TypeMatchupTest {
    private final static Type ATTACK_TYPE = new Type();
    private final static Type DEFEND_TYPE = new Type();
    private final static Double MULTIPLIER = 0.2342;

    @Test
    public void testPojo() {
        TypeMatchup matchup = new TypeMatchup();
        matchup.setAttackType(ATTACK_TYPE);
        matchup.setDefendType(DEFEND_TYPE);
        matchup.setMultiplier(MULTIPLIER);

        assertEquals(ATTACK_TYPE, matchup.getAttackType());
        assertEquals(DEFEND_TYPE, matchup.getDefendType());
        assertEquals(MULTIPLIER, matchup.getMultiplier());
    }

}