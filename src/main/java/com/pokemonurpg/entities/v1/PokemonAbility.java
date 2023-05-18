package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.pokemon.PokemonAbilityRequest;

import javax.persistence.*;

@Entity
@Table(name = "species_ability")
public class PokemonAbility {

    @EmbeddedId
    PokemonAbilityKey id;

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
    @MapsId("ability_dbid")
    @JoinColumn(name = "ability_dbid")
    @JsonIgnoreProperties({ "pokemon" })
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Ability ability;

    @Column(name = "is_hidden")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Boolean hidden = false;

    public PokemonAbility() {    }

    public PokemonAbility(PokemonAbilityRequest input, Pokemon pokemon, Ability ability) {
        this.update(input);
        this.id = new PokemonAbilityKey(pokemon.getDbid(), ability.getDbid());
        this.ability = ability;
        this.pokemon = pokemon;
    }

    public void update(PokemonAbilityRequest input) {
        setHidden(input.getHidden());
    }

    public Pokemon getSpecies() {
        return pokemon;
    }

    public void setSpecies(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        if (hidden != null) {
            this.hidden = hidden;
        }
    }

}
