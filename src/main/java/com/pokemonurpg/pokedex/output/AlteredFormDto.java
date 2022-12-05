package com.pokemonurpg.pokedex.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemonurpg.entities.v1.pokemon.Species;

import java.util.HashMap;
import java.util.Map;

public class AlteredFormDto {
    @JsonIgnoreProperties({ "classification", "height", "weight", "maleAllowed", "femaleAllowed",
            "legendaryTier", "preEvolution", "evolutionMethod", "evolutionExpRequirement",
            "preMega", "megaStone", "megaSuffix" })
    private Species species;

    private Map<String, String> attacksThatDifferByForm = new HashMap<>();

    public AlteredFormDto(Species species) {
        setSpecies(species);
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Map<String, String> getAttacksThatDifferByForm() {
        return attacksThatDifferByForm;
    }

    public String getAttackMethod(String attackName) {
        return attacksThatDifferByForm.get(attackName);
    }

    public void addMappedAttack(String attackName, String method) {
        attacksThatDifferByForm.put(attackName, method);
    }

    public void removeAttack(String attackName) {
        attacksThatDifferByForm.remove(attackName);
    }
}
