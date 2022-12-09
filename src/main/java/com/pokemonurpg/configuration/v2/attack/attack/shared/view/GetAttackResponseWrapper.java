package com.pokemonurpg.configuration.v2.attack.attack.shared.view;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pokemonurpg.configuration.v2.shared.view.EntityWrapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetAttackResponseWrapper implements EntityWrapper {
    @JsonSerialize(as = GetAttackResponse.class)
    @JsonUnwrapped
    public GetAttackResponse entity;
}
