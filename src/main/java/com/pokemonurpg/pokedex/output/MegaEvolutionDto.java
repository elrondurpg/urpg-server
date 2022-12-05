package com.pokemonurpg.pokedex.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemonurpg.entities.v1.pokemon.Species;

import java.util.List;

public class MegaEvolutionDto {
    @JsonIgnoreProperties({
            "pokemart", "storyRank", "artRank", "parkRank", "parkLocation", "contestCredits",
            "legendaryTier", "alteredFormMethod", "cosmeticForms",
            "preEvolution", "evolutionMethod", "evolutionExpRequirement" })
    private Species species;
    private List<TypeMatchupDto> typeMatchups;

    public MegaEvolutionDto(Species species) {
        setSpecies(species);
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public List<TypeMatchupDto> getTypeMatchups() {
        return typeMatchups;
    }

    public void setTypeMatchups(List<TypeMatchupDto> typeMatchups) {
        this.typeMatchups = typeMatchups;
    }
}
