package com.pokemonurpg.configuration.v1.pokemon.species.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.attack.attack.AttackViews;
import com.pokemonurpg.configuration.v1.attack.attack.model.Attack;
import com.pokemonurpg.configuration.v1.pokemon.species.SpeciesViews;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAttackInputDto;

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
    @JsonView(value = { AttackViews.Full.class })
    private Species species;

    @ManyToOne
    @MapsId("attack_dbid")
    @JoinColumn(name = "attack_dbid")
    @JsonIgnoreProperties({ "pokemon" })
    @JsonView(value = { View.MemberView.Pokemon.class, SpeciesViews.Full.class })
    private Attack attack;

    @Column
    @JsonView(value = { View.MemberView.Pokemon.class, AttackViews.Full.class, SpeciesViews.Full.class })
    private String method = "LEVEL-UP";

    @Column
    @JsonView(value = { View.MemberView.Pokemon.class, AttackViews.Full.class, SpeciesViews.Full.class })
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
