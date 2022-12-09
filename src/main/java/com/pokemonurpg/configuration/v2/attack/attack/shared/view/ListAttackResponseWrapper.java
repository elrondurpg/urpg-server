package com.pokemonurpg.configuration.v2.attack.attack.shared.view;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pokemonurpg.configuration.v2.shared.view.EntityWrapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListAttackResponseWrapper implements EntityWrapper {
    @JsonSerialize(as = ListAttackResponse.class)
    @JsonUnwrapped
    public ListAttackResponse entity;
}