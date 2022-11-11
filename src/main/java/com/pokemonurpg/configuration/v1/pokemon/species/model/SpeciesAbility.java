package com.pokemonurpg.configuration.v1.pokemon.species.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.pokemon.ability.AbilityViews;
import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.species.SpeciesViews;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAbilityInputDto;

import javax.persistence.*;

@Entity
@Table(name = "species_ability")
public class SpeciesAbility {

    @EmbeddedId
    SpeciesAbilityKey id;

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
    @MapsId("ability_dbid")
    @JoinColumn(name = "ability_dbid")
    @JsonIgnoreProperties({ "pokemon" })
    @JsonView(value = { SpeciesViews.Full.class })
    private Ability ability;

    @Column(name = "is_hidden")
    @JsonView(value = { SpeciesViews.Full.class, AbilityViews.Full.class })
    private Boolean hidden = false;

    public SpeciesAbility() {    }

    public SpeciesAbility(SpeciesAbilityInputDto input, Species species, Ability ability) {
        this.update(input);
        this.id = new SpeciesAbilityKey(species.getDbid(), ability.getDbid());
        this.ability = ability;
        this.species = species;
    }

    public void update(SpeciesAbilityInputDto input) {
        setHidden(input.getHidden());
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
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
