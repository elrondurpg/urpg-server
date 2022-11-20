package com.pokemonurpg.configuration.v1.gym.gym.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.gym.badge.model.Badge;
import com.pokemonurpg.configuration.v1.gym.gym.GymViews;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.model.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.stats.models.GymVictory;
import com.pokemonurpg.stats.models.OwnedPokemon;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@JsonView(value = { GymViews.Full.class })
@Getter
@Setter
public class Gym extends NamedConfigurationModel {

    @OneToOne
    @JoinColumn(name = "badge_dbid")
    @JsonView(value = { GymViews.Brief.class })
    private Badge badge;

    @OneToOne
    @JoinColumn(name = "type_dbid")
    @JsonView(value = { GymViews.Brief.class })
    private Type type;

    @OneToMany(mappedBy="gym")
    private List<GymVictory> victories = new ArrayList<>();

    @ManyToMany(
            targetEntity= OwnedPokemon.class,
            cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name="GYM_POKEMON",
            joinColumns=@JoinColumn(name="GYM_DBID"),
            inverseJoinColumns=@JoinColumn(name="POKEMON_DBID")
    )
    private Set<OwnedPokemon> pokemon = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "term_dbid")
    @JsonView(value = { GymViews.Brief.class })
    private GymOwnershipTerm currentOwnerRecord;
}
