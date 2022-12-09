package com.pokemonurpg.configuration.v1.pokemon.species.shared.view;
import com.fasterxml.jackson.annotation.JsonView;

public interface GetSpeciesSpeciesAbilityView {
    
    @JsonView(GetSpeciesView.class) 
    GetSpeciesAbilityView getAbility();
    Boolean getHidden();
}
