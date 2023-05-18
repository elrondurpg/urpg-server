package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.abilities.AbilityRequest;
import com.pokemonurpg.lib.v1.models.NamedObject;

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
    private Set<PokemonAbility> pokemon;

    public Ability() {}

    public Ability(AbilityRequest input) {
        this.update(input);
    }

    public void update(AbilityRequest input) {
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

    public Set<PokemonAbility> getPokemon() {
        return pokemon;
    }

    public void setPokemon(Set<PokemonAbility> pokemon) {
        this.pokemon = pokemon;
    }
}
