package com.pokemonurpg.configuration.v3.shared.gateway;

import com.pokemonurpg.configuration.v3.shared.request.ListRequest;
import com.pokemonurpg.configuration.v3.shared.response.PagedResponse;

public interface ListDataGateway
<
    View,
    Request extends ListRequest
> {
    PagedResponse<View> getList(Request request);
}
