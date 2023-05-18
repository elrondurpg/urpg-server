package com.pokemonurpg.pokedex.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemonurpg.entities.v1.Pokemon;

import java.util.HashMap;
import java.util.Map;

public class AlteredFormResponse {
    @JsonIgnoreProperties({ "classification", "height", "weight", "maleAllowed", "femaleAllowed",
            "legendaryTier", "preEvolution", "evolutionMethod", "evolutionExpRequirement",
            "preMega", "megaStone", "megaSuffix" })
    private Pokemon pokemon;

    private Map<String, String> attacksThatDifferByForm = new HashMap<>();

    public AlteredFormResponse(Pokemon pokemon) {
        setSpecies(pokemon);
    }

    public Pokemon getSpecies() {
        return pokemon;
    }

    public void setSpecies(Pokemon pokemon) {
        this.pokemon = pokemon;
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
