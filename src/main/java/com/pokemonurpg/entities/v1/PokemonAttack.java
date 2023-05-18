package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.pokemon.PokemonAttackRequest;

import javax.persistence.*;

@Entity
@Table(name = "species_attack")
public class PokemonAttack {

    @EmbeddedId
    PokemonAttackKey id;

    @ManyToOne
    @MapsId("species_dbid")
    @JoinColumn(name = "species_dbid")
    @JsonIgnoreProperties({ "abilities", "attacks", "classification", "hp", "attack", "defense", "specialAttack",
            "specialDefense", "speed", "height", "weight", "maleAllowed", "femaleAllowed", "pokemart",
            "storyRank", "artRank", "parkRank", "parkLocation", "contestCredits", "legendaryTier", "cosmeticForms",
            "alteredFormMethod", "formName", "preEvolution", "evolutionMethod", "evolutionExpRequirement",
            "preMega", "megaStone", "megaSuffix" })
    private Pokemon pokemon;

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

    public PokemonAttack() {    }

    public PokemonAttack(PokemonAttackRequest input, Pokemon pokemon, Attack attack) {
        this.update(input);
        this.id = new PokemonAttackKey(pokemon.getDbid(), attack.getDbid());
        this.attack = attack;
        this.pokemon = pokemon;
    }

    public void update(PokemonAttackRequest input) {
        setMethod(input.getMethod());
        setGeneration(input.getGeneration());
    }

    public Pokemon getSpecies() {
        return pokemon;
    }

    public void setSpecies(Pokemon pokemon) {
        this.pokemon = pokemon;
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
