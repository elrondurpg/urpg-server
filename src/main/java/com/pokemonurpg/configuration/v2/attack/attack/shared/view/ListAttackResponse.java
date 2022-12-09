package com.pokemonurpg.configuration.v2.attack.attack.shared.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pokemonurpg.configuration.v2.shared.view.NamedResponse;

public interface ListAttackResponse extends NamedResponse {
    Integer getDbid();
    String getName();

    @JsonSerialize(as = NamedResponse.class)
    NamedResponse getType();

    Integer getPower();
    Integer getAccuracy(); 
    Integer getPp();

    @JsonSerialize(as = NamedResponse.class)
    NamedResponse getCategory();
}
