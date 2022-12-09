package com.pokemonurpg.configuration.v1.pokemon.species.shared.view;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v2.shared.view.NamedResponse;

public interface ListSpeciesView extends NamedResponse {
    @JsonView(value = { ListSpeciesView.class })
    Integer getDexno();
    
    @JsonView(value = { ListSpeciesView.class })
    ListSpeciesTypeView getType1();

    @JsonView(value = { ListSpeciesView.class })
    ListSpeciesTypeView getType2();

    @JsonView(value = { ListSpeciesView.class })
    String getDisplayName();

    @JsonView(value = { ListSpeciesView.class })
    String getFormName();

}
