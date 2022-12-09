package com.pokemonurpg.configuration.v2.shared.view;

import com.fasterxml.jackson.annotation.JsonView;

public interface NamedResponse extends IndexedResponse {
    @JsonView(NamedResponse.class) 
    String getName();
}
