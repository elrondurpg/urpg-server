package com.pokemonurpg.pokedex.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemonurpg.entities.v1.Pokemon;

import java.util.List;

public class MegaEvolutionResponse {
    @JsonIgnoreProperties({
            "pokemart", "storyRank", "artRank", "parkRank", "parkLocation", "contestCredits",
            "legendaryTier", "alteredFormMethod", "cosmeticForms",
            "preEvolution", "evolutionMethod", "evolutionExpRequirement" })
    private Pokemon pokemon;
    private List<TypeMatchupResponse> typeMatchups;

    public MegaEvolutionResponse(Pokemon pokemon) {
        setSpecies(pokemon);
    }

    public Pokemon getSpecies() {
        return pokemon;
    }

    public void setSpecies(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public List<TypeMatchupResponse> getTypeMatchups() {
        return typeMatchups;
    }

    public void setTypeMatchups(List<TypeMatchupResponse> typeMatchups) {
        this.typeMatchups = typeMatchups;
    }
}
