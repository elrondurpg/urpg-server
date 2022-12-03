package com.pokemonurpg.configuration.v3.shared.interactor;

import com.pokemonurpg.configuration.v3.shared.gateway.ListDataGateway;
import com.pokemonurpg.configuration.v3.shared.request.ListRequest;
import com.pokemonurpg.configuration.v3.shared.response.PagedResponse;

public abstract class ListInteractor<View, Request extends ListRequest> implements ListInputBoundary<View, Request>{
    private ListDataGateway<View, Request> gateway;

    public ListInteractor(ListDataGateway<View, Request> gateway) {
        this.gateway = gateway;
    }

    @Override
    public PagedResponse<View> getList(Request request) {
        return gateway.getList(request);
    }
}
