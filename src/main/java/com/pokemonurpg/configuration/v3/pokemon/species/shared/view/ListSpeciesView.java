package com.pokemonurpg.configuration.v3.pokemon.species.shared.view;

import com.pokemonurpg.configuration.v3.shared.view.NamedEntityResponseView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = ListSpeciesView.class)
public interface ListSpeciesView extends NamedEntityResponseView {
    Integer getDexno();
    ListSpeciesTypeView getType1();
    ListSpeciesTypeView getType2();
    String getDisplayName();
}
