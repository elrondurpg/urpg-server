package com.pokemonurpg.configuration.v3.pokemon.species.list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.ListSpeciesView;
import com.pokemonurpg.configuration.v3.shared.response.PagedResponse;

public class ListSpeciesInteractorTest {

    private ListSpeciesInteractor interactor;
    private ListSpeciesDataGatewayFake gateway;

    @BeforeEach
    public void setup() {
        gateway = new ListSpeciesDataGatewayFake();
        interactor = new ListSpeciesInteractor(gateway);
    }

    @Test
    void testGet() {
        ListSpeciesRequest request = new ListSpeciesRequest();
        PagedResponse<ListSpeciesView> response = interactor.getList(request);
        assertEquals(ListSpeciesDataGatewayFake.RESPONSE, response);
        assertEquals(request, gateway.getRequestArg());
    }
}
