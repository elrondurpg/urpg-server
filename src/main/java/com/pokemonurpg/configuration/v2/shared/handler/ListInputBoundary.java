package com.pokemonurpg.configuration.v2.shared.handler;

import com.pokemonurpg.configuration.v2.shared.request.ListRequest;
import com.pokemonurpg.configuration.v2.shared.view.ListResponse;

public interface ListInputBoundary<RequestType extends ListRequest, ResponseType> {
    ListResponse<ResponseType> getList(RequestType request);
}
