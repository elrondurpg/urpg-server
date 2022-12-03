package com.pokemonurpg.configuration.v3.pokemon.species.get;

import com.pokemonurpg.configuration.v3.shared.response.PagedResponse;
import com.pokemonurpg.configuration.v3.shared.view.NamedEntityResponseView;

public interface GetPagedSpeciesConfigurationInputBoundary {
    PagedResponse<? extends NamedEntityResponseView> get(GetPagedSpeciesConfigurationRequest request);
}
