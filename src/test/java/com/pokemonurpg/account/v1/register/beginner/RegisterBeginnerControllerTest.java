package com.pokemonurpg.account.v1.register.beginner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegisterBeginnerControllerTest {

    private RegisterBeginnerController controller;

    @BeforeEach
    public void setup() {
        controller = new RegisterBeginnerController(new RegisterBeginnerHandlerFake());
    }

    @Test
    void test_register() {
        RegisterBeginnerResponse response = controller.register(RegisterBeginnerHandlerFake.VALID_REQUEST);
        assertEquals(RegisterBeginnerHandlerFake.VALID_RESPONSE, response);
    }
}
