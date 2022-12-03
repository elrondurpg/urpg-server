package com.pokemonurpg.configuration.v3.pokemon.species.shared.view;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = GetSpeciesCosmeticFormView.class)
public interface GetSpeciesCosmeticFormView {
    String getName();
    String getFormName();
}
