package com.pokemonurpg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.abilities.AbilityInputDto;
import com.pokemonurpg.lib.model.NamedObject;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Ability implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    @JsonView(value = { View.MemberView.Pokemon.class })
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "ability")
    @JsonIgnoreProperties({ "ability" })
    private Set<SpeciesAbility> pokemon;

    public Ability() {}

    public Ability(AbilityInputDto input) {
        this.update(input);
    }

    public void update(AbilityInputDto input) {
        setName(input.getName());
        setDescription(input.getDescription());
    }

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
