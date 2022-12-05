package com.pokemonurpg.configuration.v1.attack.attack.shared.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pokemonurpg.configuration.v1.shared.view.NamedResponse;

@JsonSerialize(as = ListAttackResponse.class)
public interface ListAttackResponse extends NamedResponse {
    NamedResponse getType();
    Integer getPower();
    Integer getAccuracy();
    Integer getPp();
    NamedResponse getCategory();
}
