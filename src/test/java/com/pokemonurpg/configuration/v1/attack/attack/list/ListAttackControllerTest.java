package com.pokemonurpg.configuration.v1.attack.attack.list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pokemonurpg.configuration.v1.attack.attack.shared.view.ListAttackResponse;
import com.pokemonurpg.configuration.v1.shared.view.ListView;

public class ListAttackControllerTest {
    private ListAttackController controller;
    private ListAttackHandlerFake handler;

    @BeforeEach
    public void setup() {
        handler = new ListAttackHandlerFake();
        controller = new ListAttackController(handler);
    }

    @Test
    public void test_getList() {
        ListAttackRequest request = new ListAttackRequest();
        ListView<ListAttackResponse> response = controller.getList(request);
        assertEquals(ListAttackHandlerFake.ATTACKS, response.getContent());
        assertEquals(request, handler.getRequestArg());
    }
}
