package com.pokemonurpg.configuration.v1.pokemon.species.shared.view;
import com.fasterxml.jackson.annotation.JsonView;

public interface GetSpeciesCosmeticFormView {
    
    @JsonView(GetSpeciesView.class) 
    String getName();
    
    @JsonView(GetSpeciesView.class) 
    String getFormName();
}
