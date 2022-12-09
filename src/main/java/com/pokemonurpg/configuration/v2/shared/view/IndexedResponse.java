package com.pokemonurpg.configuration.v2.shared.view;

import com.fasterxml.jackson.annotation.JsonView;

public interface IndexedResponse {
    @JsonView(IndexedResponse.class) 
    Integer getDbid();
}
