package com.pokemonurpg.configuration.v1.pokemon.ability.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.lib.model.ConfigurationModel;
import com.pokemonurpg.configuration.v1.lib.view.ConfigurationViews;
import com.pokemonurpg.configuration.v1.pokemon.ability.input.AbilityInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAbility;
import com.pokemonurpg.core.model.NamedObject;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonView(value = { ConfigurationViews.V1.Pokemon.Ability.Full.class })
public class Ability extends ConfigurationModel implements NamedObject {

    @JsonView(value = { ConfigurationViews.V1.Pokemon.Ability.Id.class, ConfigurationViews.V1.Pokemon.Species.Full.class })
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @JsonView(value = { ConfigurationViews.V1.Pokemon.Ability.Id.class, ConfigurationViews.V1.Pokemon.Species.Full.class })
    @Column
    private String name;

    @JsonView(value = { ConfigurationViews.V1.Pokemon.Ability.Brief.class, ConfigurationViews.V1.Pokemon.Species.Full.class })
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
