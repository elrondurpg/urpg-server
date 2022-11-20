package com.pokemonurpg.configuration.v1.gym.champion.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.gym.champion.ChampionViews;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.model.ChampionOwnershipTerm;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;
import com.pokemonurpg.stats.models.OwnedPokemon;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@JsonView(value = { ChampionViews.Full.class })
@Getter
@Setter
public class Champion extends NamedConfigurationModel {

    @ManyToMany(
            targetEntity= OwnedPokemon.class,
            cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name="ELITE_FOUR_POKEMON",
            joinColumns=@JoinColumn(name="SLOT_DBID"),
            inverseJoinColumns=@JoinColumn(name="POKEMON_DBID")
    )
    private Set<OwnedPokemon> pokemon = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "term_dbid")
    @JsonView(value = { ChampionViews.Brief.class })
    private ChampionOwnershipTerm currentOwnerRecord;
}
