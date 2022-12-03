package com.pokemonurpg.configuration.v3.pokemon.species.shared.view;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = GetSpeciesSpeciesAttackView.class)
public interface GetSpeciesSpeciesAttackView {
    GetSpeciesAttackView getAttack();
    String getMethod();
    Integer getGeneration();
}
