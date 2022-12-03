package com.pokemonurpg.configuration.v3.pokemon.species.shared.view;

import com.pokemonurpg.configuration.v3.shared.view.NamedEntityResponseView;

public interface SpeciesBriefResponseView extends NamedEntityResponseView {
    Integer getDexno();
    SpeciesBriefResponseTypeView getType1();
    SpeciesBriefResponseTypeView getType2();
    String getDisplayName();
}
