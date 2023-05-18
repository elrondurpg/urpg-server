package com.pokemonurpg.pokedex.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemonurpg.entities.v1.Species;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PokedexEntryDto {

    private Species species;
    @JsonIgnoreProperties({ "dbid", "displayName", "formName",
            "type1", "type2", "attacks", "abilities",
            "hp", "attack", "defense", "specialAttack", "specialDefense", "speed",
            "classification", "height", "weight", "maleAllowed", "femaleAllowed",
            "pokemart", "storyRank", "artRank", "parkRank", "parkLocation", "contestCredits",
            "legendaryTier", "alteredFormMethod", "cosmeticForms",
            "preEvolution", "evolutionMethod", "evolutionExpRequirement",
            "preMega", "megaStone", "megaSuffix" })
    private Species prevDex;
    @JsonIgnoreProperties({ "dbid", "displayName", "formName",
            "type1", "type2", "attacks", "abilities",
            "hp", "attack", "defense", "specialAttack", "specialDefense", "speed",
            "classification", "height", "weight", "maleAllowed", "femaleAllowed",
            "pokemart", "storyRank", "artRank", "parkRank", "parkLocation", "contestCredits",
            "legendaryTier", "alteredFormMethod", "cosmeticForms",
            "preEvolution", "evolutionMethod", "evolutionExpRequirement",
            "preMega", "megaStone", "megaSuffix" })
    private Species nextDex;
    private List<AlteredFormDto> alteredForms;
    @JsonIgnoreProperties({ "formName", "type1", "type2", "attacks", "abilities",
            "hp", "attack", "defense", "specialAttack", "specialDefense", "speed",
            "classification", "height", "weight", "maleAllowed", "femaleAllowed",
            "pokemart", "storyRank", "artRank", "parkRank", "parkLocation", "contestCredits",
            "legendaryTier", "alteredFormMethod", "cosmeticForms",
            "preMega", "megaStone", "megaSuffix" })
    private List<List<Species>> evolutionFamily;
    private List<MegaEvolutionDto> megaEvolutions;
    private List<TypeMatchupDto> typeMatchups;
    private Set<String> attacksThatDifferByForm = new HashSet<>();

    public PokedexEntryDto() {}

    public PokedexEntryDto(Species species) {
        setSpecies(species);
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Species getPrevDex() {
        return prevDex;
    }

    public void setPrevDex(Species prevDex) {
        this.prevDex = prevDex;
    }

    public Species getNextDex() {
        return nextDex;
    }

    public void setNextDex(Species nextDex) {
        this.nextDex = nextDex;
    }

    public List<AlteredFormDto> getAlteredForms() {
        return alteredForms;
    }

    public void setAlteredForms(List<AlteredFormDto> alteredForms) {
        this.alteredForms = alteredForms;
    }

    public List<List<Species>> getEvolutionFamily() {
        return evolutionFamily;
    }

    public void setEvolutionFamily(List<List<Species>> evolutionFamily) {
        this.evolutionFamily = evolutionFamily;
    }

    public List<MegaEvolutionDto> getMegaEvolutions() {
        return megaEvolutions;
    }

    public void setMegaEvolutions(List<MegaEvolutionDto> megaEvolutions) {
        this.megaEvolutions = megaEvolutions;
    }

    public List<TypeMatchupDto> getTypeMatchups() {
        return typeMatchups;
    }

    public void setTypeMatchups(List<TypeMatchupDto> typeMatchups) {
        this.typeMatchups = typeMatchups;
    }

    public Set<String> getAttacksThatDifferByForm() {
        return attacksThatDifferByForm;
    }

    public void buildAttacksThatDifferByForm() {
        alteredForms.forEach(alteredForm -> attacksThatDifferByForm.addAll(alteredForm.getAttacksThatDifferByForm().keySet()));
    }
}
