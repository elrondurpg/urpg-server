package com.pokemonurpg.configuration.v1.shared.handler;

import com.pokemonurpg.configuration.v1.shared.request.ListRequest;
import com.pokemonurpg.configuration.v1.shared.view.ListResponse;

public interface ListInputBoundary<RequestType extends ListRequest, ResponseType> {
    ListResponse<ResponseType> getList(RequestType request);
}
