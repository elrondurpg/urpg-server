package com.pokemonurpg.configuration.v3.pokemon.species.list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.ListSpeciesView;
import com.pokemonurpg.configuration.v3.shared.response.PagedResponse;

public class ListSpeciesControllerTest {
    
    private ListSpeciesController controller;
    private ListSpeciesInteractorFake interactor;

    @BeforeEach
    public void setup() {
        interactor = new ListSpeciesInteractorFake();
        controller = new ListSpeciesController(interactor);
    }

    @Test
    public void test_getList() {
        ListSpeciesRequest request = new ListSpeciesRequest();
        PagedResponse<ListSpeciesView> response = controller.getList(request);
        assertEquals(ListSpeciesInteractorFake.RESPONSE, response);
        assertEquals(request, interactor.getRequestArg());
    }

}
