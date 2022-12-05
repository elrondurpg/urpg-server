package com.pokemonurpg.entities.v1.gym;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.pokemonurpg.entities.v1.shared.NamedEntity;
import com.pokemonurpg.entities.v1.stats.OwnedPokemon;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Champion extends NamedEntity {

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
    private ChampionOwnershipTerm currentOwnerRecord;
}
