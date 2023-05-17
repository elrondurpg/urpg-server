package com.pokemonurpg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.pokemon.SpeciesAttackInputDto;

import javax.persistence.*;

@Entity
@Table(name = "species_attack")
public class SpeciesAttack {

    @EmbeddedId
    SpeciesAttackKey id;

    @ManyToOne
    @MapsId("species_dbid")
    @JoinColumn(name = "species_dbid")
    @JsonIgnoreProperties({ "abilities", "attacks", "classification", "hp", "attack", "defense", "specialAttack",
            "specialDefense", "speed", "height", "weight", "maleAllowed", "femaleAllowed", "pokemart",
            "storyRank", "artRank", "parkRank", "parkLocation", "contestCredits", "legendaryTier", "cosmeticForms",
            "alteredFormMethod", "formName", "preEvolution", "evolutionMethod", "evolutionExpRequirement",
            "preMega", "megaStone", "megaSuffix" })
    private Species species;

    @ManyToOne
    @MapsId("attack_dbid")
    @JoinColumn(name = "attack_dbid")
    @JsonIgnoreProperties({ "pokemon" })
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Attack attack;

    @Column
    @JsonView(value = { View.MemberView.Pokemon.class })
    private String method = "LEVEL-UP";

    @Column
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Integer generation;

    public SpeciesAttack() {    }

    public SpeciesAttack(SpeciesAttackInputDto input, Species species, Attack attack) {
        this.update(input);
        this.id = new SpeciesAttackKey(species.getDbid(), attack.getDbid());
        this.attack = attack;
        this.species = species;
    }

    public void update(SpeciesAttackInputDto input) {
        setMethod(input.getMethod());
        setGeneration(input.getGeneration());
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Attack getAttack() {
        return attack;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        if (method != null) {
            this.method = method;
        }
    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        if (method != null) {
            this.generation = generation;
        }
    }
}
