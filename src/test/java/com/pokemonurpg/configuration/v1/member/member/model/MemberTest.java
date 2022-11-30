package com.pokemonurpg.configuration.v1.member.member.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.pokemonurpg.configuration.v1.pokemon.capturemethod.model.CaptureMethod;
import com.pokemonurpg.stats.models.OwnedPokemon;

public class MemberTest {

    @Test
    public void test_setDefaultValues() {
        Member member = new Member();
        member.setDefaultValues();
        assertFalse(member.getBot());
        assertNotNull(member.getSalt());
    }

    @Test
    public void test_setDefaultValues_WhenAlreadyProvided() {
        Member member = new Member();
        member.setBot(true);
        member.setDefaultValues();
        assertTrue(member.getBot());
        assertNotNull(member.getSalt());
    }

    @Test
    public void test_hasStarter() {
        Member member = new Member();

        CaptureMethod method = new CaptureMethod();
        method.setName("Starter");

        OwnedPokemon pokemon = new OwnedPokemon();
        pokemon.setObtained(method);

        member.setPokemon(Collections.singletonList(pokemon));
        assertTrue(member.hasStarter());
    }
}
