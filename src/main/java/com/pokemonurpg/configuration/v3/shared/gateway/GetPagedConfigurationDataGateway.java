package com.pokemonurpg.configuration.v3.shared.gateway;

import com.pokemonurpg.configuration.v3.shared.request.GetParams;
import com.pokemonurpg.configuration.v3.shared.response.PagedResponse;
import com.pokemonurpg.configuration.v3.shared.view.NamedEntityResponseView;

public interface GetPagedConfigurationDataGateway
<
    IdView extends NamedEntityResponseView,
    BriefView extends IdView,
    FullView extends BriefView, 
    Request extends GetParams
> {
    PagedResponse<? extends IdView> getWithIdView(Request request);
    PagedResponse<? extends BriefView> getWithBriefView(Request request);
    PagedResponse<? extends FullView> getWithFullView(Request request);
}
