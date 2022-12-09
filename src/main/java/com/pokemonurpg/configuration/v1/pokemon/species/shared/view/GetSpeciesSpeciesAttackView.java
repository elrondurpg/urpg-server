package com.pokemonurpg.configuration.v1.pokemon.species.shared.view;
import com.fasterxml.jackson.annotation.JsonView;

public interface GetSpeciesSpeciesAttackView {
    
    @JsonView(GetSpeciesView.class) 
    GetSpeciesAttackView getAttack();
    
    @JsonView(GetSpeciesView.class) 
    String getMethod();
    
    @JsonView(GetSpeciesView.class) 
    Integer getGeneration();
}
