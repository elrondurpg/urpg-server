package com.pokemonurpg.configuration.v1.pokemon.species.shared.view;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v2.shared.view.NamedResponse;

public interface GetSpeciesAttackTargetTypeView extends NamedResponse {
    
    @JsonView(GetSpeciesView.class) 
    String getDescription();
}
