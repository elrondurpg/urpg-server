package com.pokemonurpg.configuration.v2.attack.attack.getByName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pokemonurpg.configuration.v2.attack.attack.shared.view.GetAttackResponse;
import com.pokemonurpg.entities.v1.attack.AttackRepositoryFake;

public class GetAttackByNameHandlerTest {

    private GetAttackByNameHandler handler;
    private AttackRepositoryFake repository;

    @BeforeEach
    public void setup() {
        repository = new AttackRepositoryFake();
        handler = new GetAttackByNameHandler(repository);
    }

    @Test
    void test_getByName_whenNameIsExact() {
        String name = AttackRepositoryFake.LONG_NAME; 
        GetAttackResponse response = handler.getByName(name);
        assertEquals(name, repository.getFindByNameArg());
        assertNull(repository.getFindFirstByNameStartingWithArg());
        assertEquals(AttackRepositoryFake.ATTACK, response);
    }

    @Test
    void test_getByName_whenNameIsAbbreviated() {
        String name = AttackRepositoryFake.SHORT_NAME; 
        GetAttackResponse response = handler.getByName(name);
        assertEquals(name, repository.getFindByNameArg());
        assertEquals(name, repository.getFindFirstByNameStartingWithArg());
        assertEquals(AttackRepositoryFake.ATTACK, response);
    }
}
