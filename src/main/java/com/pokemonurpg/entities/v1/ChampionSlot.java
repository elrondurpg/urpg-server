package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.lib.v1.models.NamedObject;
import com.pokemonurpg.configuration.v1.championslots.ChampionSlotRequest;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
@Table(name = "champion")
public class ChampionSlot implements NamedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

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
    @JsonIgnoreProperties({ "slot" })
    private ChampionRecord currentOwnerRecord;

    public ChampionSlot() {
    }

    public ChampionSlot(ChampionSlotRequest input) {
        this.update(input);
    }

    public void update(ChampionSlotRequest input) {
        setName(input.getName());
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

    public ChampionRecord getCurrentOwnerRecord() {
        return currentOwnerRecord;
    }

    public void setCurrentOwnerRecord(ChampionRecord currentOwnerRecord) {
        this.currentOwnerRecord = currentOwnerRecord;
    }

    public Set<OwnedPokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(Set<OwnedPokemon> pokemon) {
        this.pokemon = pokemon;
    }
}
