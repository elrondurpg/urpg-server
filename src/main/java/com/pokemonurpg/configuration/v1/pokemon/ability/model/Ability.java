package com.pokemonurpg.configuration.v1.pokemon.ability.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.lib.model.ConfigurationModel;
import com.pokemonurpg.configuration.v1.pokemon.ability.AbilityViews;
import com.pokemonurpg.configuration.v1.pokemon.species.SpeciesViews;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAbility;
import com.pokemonurpg.core.model.NamedObject;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonView(value = { AbilityViews.Full.class })
public class Ability extends ConfigurationModel implements NamedObject {

    @JsonView(value = { AbilityViews.Id.class, SpeciesViews.Full.class })
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @JsonView(value = { AbilityViews.Id.class, SpeciesViews.Full.class })
    @Column
    private String name;

    @JsonView(value = { AbilityViews.Brief.class, SpeciesViews.Full.class })
    @Column
    private String description;

    @OneToMany(mappedBy = "ability")
    private Set<SpeciesAbility> pokemon;

    public Ability() {}

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        }
    }

    public Set<SpeciesAbility> getPokemon() {
        return pokemon;
    }

    public void setPokemon(Set<SpeciesAbility> pokemon) {
        this.pokemon = pokemon;
    }
}
