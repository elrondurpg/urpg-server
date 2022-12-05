package com.pokemonurpg.entities.v1.gym;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.pokemonurpg.entities.v1.member.GymVictory;
import com.pokemonurpg.entities.v1.pokemon.Type;
import com.pokemonurpg.entities.v1.shared.NamedEntity;
import com.pokemonurpg.entities.v1.stats.OwnedPokemon;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Gym extends NamedEntity {

    @OneToOne
    @JoinColumn(name = "badge_dbid")
    private Badge badge;

    @OneToOne
    @JoinColumn(name = "type_dbid")
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
    private GymOwnershipTerm currentOwnerRecord;
}
