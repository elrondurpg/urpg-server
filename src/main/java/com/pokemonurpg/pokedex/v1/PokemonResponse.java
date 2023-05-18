package com.pokemonurpg.pokedex.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemonurpg.entities.v1.Pokemon;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PokemonResponse {

    private Pokemon pokemon;
    @JsonIgnoreProperties({ "dbid", "displayName", "formName",
            "type1", "type2", "attacks", "abilities",
            "hp", "attack", "defense", "specialAttack", "specialDefense", "speed",
            "classification", "height", "weight", "maleAllowed", "femaleAllowed",
            "pokemart", "storyRank", "artRank", "parkRank", "parkLocation", "contestCredits",
            "legendaryTier", "alteredFormMethod", "cosmeticForms",
            "preEvolution", "evolutionMethod", "evolutionExpRequirement",
            "preMega", "megaStone", "megaSuffix" })
    private Pokemon prevDex;
    @JsonIgnoreProperties({ "dbid", "displayName", "formName",
            "type1", "type2", "attacks", "abilities",
            "hp", "attack", "defense", "specialAttack", "specialDefense", "speed",
            "classification", "height", "weight", "maleAllowed", "femaleAllowed",
            "pokemart", "storyRank", "artRank", "parkRank", "parkLocation", "contestCredits",
            "legendaryTier", "alteredFormMethod", "cosmeticForms",
            "preEvolution", "evolutionMethod", "evolutionExpRequirement",
            "preMega", "megaStone", "megaSuffix" })
    private Pokemon nextDex;
    private List<AlteredFormResponse> alteredForms;
    @JsonIgnoreProperties({ "formName", "type1", "type2", "attacks", "abilities",
            "hp", "attack", "defense", "specialAttack", "specialDefense", "speed",
            "classification", "height", "weight", "maleAllowed", "femaleAllowed",
            "pokemart", "storyRank", "artRank", "parkRank", "parkLocation", "contestCredits",
            "legendaryTier", "alteredFormMethod", "cosmeticForms",
            "preMega", "megaStone", "megaSuffix" })
    private List<List<Pokemon>> evolutionFamily;
    private List<MegaEvolutionResponse> megaEvolutions;
    private List<TypeMatchupResponse> typeMatchups;
    private Set<String> attacksThatDifferByForm = new HashSet<>();

    public PokemonResponse() {}

    public PokemonResponse(Pokemon pokemon) {
        setSpecies(pokemon);
    }

    public Pokemon getSpecies() {
        return pokemon;
    }

    public void setSpecies(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Pokemon getPrevDex() {
        return prevDex;
    }

    public void setPrevDex(Pokemon prevDex) {
        this.prevDex = prevDex;
    }

    public Pokemon getNextDex() {
        return nextDex;
    }

    public void setNextDex(Pokemon nextDex) {
        this.nextDex = nextDex;
    }

    public List<AlteredFormResponse> getAlteredForms() {
        return alteredForms;
    }

    public void setAlteredForms(List<AlteredFormResponse> alteredForms) {
        this.alteredForms = alteredForms;
    }

    public List<List<Pokemon>> getEvolutionFamily() {
        return evolutionFamily;
    }

    public void setEvolutionFamily(List<List<Pokemon>> evolutionFamily) {
        this.evolutionFamily = evolutionFamily;
    }

    public List<MegaEvolutionResponse> getMegaEvolutions() {
        return megaEvolutions;
    }

    public void setMegaEvolutions(List<MegaEvolutionResponse> megaEvolutions) {
        this.megaEvolutions = megaEvolutions;
    }

    public List<TypeMatchupResponse> getTypeMatchups() {
        return typeMatchups;
    }

    public void setTypeMatchups(List<TypeMatchupResponse> typeMatchups) {
        this.typeMatchups = typeMatchups;
    }

    public Set<String> getAttacksThatDifferByForm() {
        return attacksThatDifferByForm;
    }

    public void buildAttacksThatDifferByForm() {
        alteredForms.forEach(alteredForm -> attacksThatDifferByForm.addAll(alteredForm.getAttacksThatDifferByForm().keySet()));
    }
}
