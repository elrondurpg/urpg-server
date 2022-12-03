package com.pokemonurpg.configuration.v3.pokemon.species.shared.view;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = GetSpeciesSpeciesAbilityView.class)
public interface GetSpeciesSpeciesAbilityView {
    GetSpeciesAbilityView getAbility();
    Boolean getHidden();
}
