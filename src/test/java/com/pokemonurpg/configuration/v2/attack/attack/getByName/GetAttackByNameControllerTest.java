package com.pokemonurpg.configuration.v2.attack.attack.getByName;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pokemonurpg.configuration.v2.attack.attack.shared.view.GetAttackResponse;

public class GetAttackByNameControllerTest {

    private GetAttackByNameController controller;
    private GetAttackByNameHandlerFake handler;

    @BeforeEach
    public void setup() {
        handler = new GetAttackByNameHandlerFake();
        controller = new GetAttackByNameController(handler);
    }

    @Test
    public void test_getByName() {
        String name = GetAttackByNameHandlerFake.NAME;
        GetAttackResponse response = controller.getByName(name);
        assertEquals(name, handler.getNameArg());
        assertEquals(GetAttackByNameHandlerFake.RESPONSE, response);
    }

}
