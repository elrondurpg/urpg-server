package com.pokemonurpg.configuration.v3.pokemon.species.shared.view;

import com.pokemonurpg.configuration.v3.shared.view.NamedEntityResponseView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = GetSpeciesContestMoveTypeView.class)
public interface GetSpeciesContestMoveTypeView extends NamedEntityResponseView {
    String getDescription();
    Integer getScore();
    Integer getJam();
}
