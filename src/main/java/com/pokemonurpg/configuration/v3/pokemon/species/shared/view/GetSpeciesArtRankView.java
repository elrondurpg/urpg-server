package com.pokemonurpg.configuration.v3.pokemon.species.shared.view;

import com.pokemonurpg.configuration.v3.shared.view.NamedEntityResponseView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = GetSpeciesArtRankView.class)
public interface GetSpeciesArtRankView extends NamedEntityResponseView {
    String getRequirement();
}
