package com.pokemonurpg.configuration.v3.shared.interactor;

import com.pokemonurpg.configuration.v3.shared.request.ListRequest;
import com.pokemonurpg.configuration.v3.shared.response.PagedResponse;

public interface ListInputBoundary<View, Request extends ListRequest> {
    PagedResponse<View> getList(Request request);
}
