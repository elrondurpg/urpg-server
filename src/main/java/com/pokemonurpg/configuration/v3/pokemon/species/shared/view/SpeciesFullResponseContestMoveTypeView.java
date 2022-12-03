package com.pokemonurpg.configuration.v3.pokemon.species.shared.view;

import com.pokemonurpg.configuration.v3.shared.view.NamedEntityResponseView;

public interface SpeciesFullResponseContestMoveTypeView extends NamedEntityResponseView {
    String getDescription();
    Integer getScore();
    Integer getJam();
}
