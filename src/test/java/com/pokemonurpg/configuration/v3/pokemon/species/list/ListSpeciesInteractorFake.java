package com.pokemonurpg.configuration.v3.pokemon.species.list;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.ListSpeciesView;
import com.pokemonurpg.configuration.v3.shared.interactor.ListInputBoundary;
import com.pokemonurpg.configuration.v3.shared.response.PagedResponse;
import com.pokemonurpg.configuration.v3.shared.response.PagedResponseFake;

import lombok.Getter;

@Getter
public class ListSpeciesInteractorFake implements ListInputBoundary<ListSpeciesView, ListSpeciesRequest> {
    public final static PagedResponseFake<ListSpeciesView> RESPONSE = new PagedResponseFake<>();
    private ListSpeciesRequest requestArg;

    @Override
    public PagedResponse<ListSpeciesView> getList(ListSpeciesRequest request) {
        requestArg = request;
        return RESPONSE;
    }
}
